package com.example.kotkin_team.profile.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import com.example.kotkin_team.R
import com.example.kotkin_team.profile.domain.model.Profile
import com.example.kotkin_team.profile.domain.model.Recipe
import com.example.kotkin_team.profile.presentation.made_recipes.RecipeListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

private const val ARG_PROFILE_ID = "profileId"

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    companion object {
        fun newInstance(profileId: Int) =
            ProfileFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PROFILE_ID, profileId)
                }
            }
    }


    private var profileId: Int = -1
    private val viewModel by viewModels<ProfileViewModel>()
    private val recipeListAdapter = RecipeListAdapter { recipe: Recipe ->  }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.onEvent(ProfileFragmentEvents.LoadProfile(profileId))
        val profile = viewModel.stateProfile.value.profile
        if (profile != null) {
            viewModel.onEvent(ProfileFragmentEvents.LoadMadeRecipes(profile))
        }

        val profileAvatarImageView = view.findViewById<ImageView>(R.id.avatar_image_view)
        bindImage(profile, profileAvatarImageView)
        val profileNameTextField = view.findViewById<TextView>(R.id.profile_name_text_view)
        profileNameTextField.text = profile?.name
        val profileSecondNameTextField = view.findViewById<TextView>(R.id.profile_second_name_text_view)
        profileSecondNameTextField.text = profile?.secondName
        val settingsButton = view.findViewById<ImageView>(R.id.profile_settings_button)
        settingsButton.setOnClickListener {
            TODO()
        }

        val madeRecipesRecyclerView = view.findViewById<RecyclerView>(R.id.made_recipes_recycler_view)
        madeRecipesRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            adapter = recipeListAdapter
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                viewModel.stateMadeRecipes.collectLatest {
                    when(it.isLoading) {
                        true -> {

                        }
                        false -> {
                            if (it.error.isBlank()) {
                                recipeListAdapter.submitList(it.madeRecipes)
                            } else {
                                Toast.makeText(context, it.error, Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
        }
    }

    private fun bindImage(profile: Profile?, avatarImage: ImageView) {
        val url = profile?.image ?: "https://sun9-9.userapi.com/impf/wpKh_I1m4InpdEDsX31RH4Fh2eLb3j-Bo9iA4A/4VibiWNxgdg.jpg?size=604x453&quality=96&sign=b9428274e0de3250c73c22b04d9f9173&c_uniq_tag=LRvg-JVGoUInZ0oqC--Fg1GaVjy84CrtSmJ_NrV8n7M&type=album"
        view?.let {
            Glide.with(it)
                .load(url)
                .override(Target.SIZE_ORIGINAL)
                .into(avatarImage)
            avatarImage.setBackgroundColor(0xFF00FF00.toInt())
        }
    }
}