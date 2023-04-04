package com.example.kotkin_team.profile.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotkin_team.R
import com.example.kotkin_team.common.view_binding.viewBinding
import com.example.kotkin_team.databinding.FragmentProfileBinding
import com.example.kotkin_team.profile.domain.model.MadeRecipe
import com.example.kotkin_team.profile.presentation.made_recipes.RecipeListAdapter
import com.example.kotkin_team.profile.presentation.made_recipes.load_state.MadeRecipesLoadStateAdapter
import com.example.kotkin_team.profile.presentation.util.bindImage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    companion object {
        fun newInstance() = ProfileFragment()
    }

    private val binding by viewBinding(FragmentProfileBinding::bind)
    private val viewModel by viewModels<ProfileViewModel>()
    private val recipeListAdapter = RecipeListAdapter { madeRecipe: MadeRecipe ->
        goToRecipeFragment(madeRecipe)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRetry.setOnClickListener {
            viewModel.onEvent(ProfileFragmentEvents.LoadProfile)
        }

        binding.profileLogOutButton.setOnClickListener {
            Toast.makeText(context, "Can't log out yet)", Toast.LENGTH_SHORT).show()
        }

        setupMadeRecipesRecyclerView()

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.stateProfile.collectLatest { profileState ->
                when (profileState.isLoading) {
                    true -> {
                        binding.mainProgressBar.visibility = View.VISIBLE
                    }
                    false -> {
                        binding.mainProgressBar.visibility = View.GONE
                        binding.btnRetry.visibility = View.GONE
                        binding.profileInformationConstraintLayout.visibility = View.VISIBLE
                        binding.madeRecipesConstraintLayout.visibility = View.VISIBLE

                        if (profileState.error.isBlank()) {
                            val profile = viewModel.stateProfile.value.profile

                            profile?.let {
                                binding.profileNameTextView.text = profile.name
                                binding.profileSecondNameTextView.text = profile.secondName
                                bindImage(view, profile.image, binding.avatarImageView)

                                viewModel.onEvent(ProfileFragmentEvents.LoadMadeRecipes)
                                viewModel.madeRecipes.buffer().collectLatest { pagingData ->
                                    withContext(Dispatchers.IO) {
                                        recipeListAdapter.submitData(pagingData)
                                    }
                                }
                            }
                        } else {
                            binding.btnRetry.visibility = View.VISIBLE
                            Toast.makeText(context, profileState.error, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    private fun setupMadeRecipesRecyclerView() {
        val loadStateAdapter = MadeRecipesLoadStateAdapter()
        binding.madeRecipesRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            adapter = recipeListAdapter.apply {
                stateRestorationPolicy =
                    RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
            }.withLoadStateFooter(footer = loadStateAdapter)
        }
        recipeListAdapter.addLoadStateListener { loadState ->
            val isListEmpty = loadState.refresh is LoadState.NotLoading &&
                    recipeListAdapter.itemCount == 0
            binding.madeRecipesRecyclerView.isVisible = !isListEmpty
            val errorState = when {
                loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                else -> null
            }
            errorState?.let {
                Toast.makeText(context, it.error.message, Toast.LENGTH_SHORT).show()
                recipeListAdapter.retry()
            }
        }
    }

    private fun goToRecipeFragment(madeRecipe: MadeRecipe) {
        Toast.makeText(context, "You have selected ${madeRecipe.title}", Toast.LENGTH_SHORT).show()
        viewModel.onEvent(ProfileFragmentEvents.LoadRecipe(madeRecipe))
    }
}
