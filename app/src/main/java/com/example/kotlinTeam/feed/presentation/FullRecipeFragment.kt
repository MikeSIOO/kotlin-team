package com.example.kotlinTeam.feed.presentation

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinTeam.R
import com.example.kotlinTeam.common.viewBinding.viewBinding
import com.example.kotlinTeam.databinding.FragmentFullRecipeBinding
import com.example.kotlinTeam.feed.domain.IngredientsAdapter
import com.example.kotlinTeam.feed.domain.StepsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FullRecipeFragment : Fragment(R.layout.fragment_full_recipe) {
    private val binding by viewBinding(FragmentFullRecipeBinding::bind)
    private val viewModel: FeedViewModel by activityViewModels()
    private val ingredientsRecycler get() = binding.ingredientsRecycler
    private val stepsRecycler get() = binding.stepsRecycler
    private val expandableTextView get() = binding.dishInfo
    private var recipeId: String? = null

    companion object {
        private const val RECIPE_ID = "recipeId"

        @JvmStatic
        fun newInstance(recipeId: String) =
            FullRecipeFragment().apply {
                arguments = Bundle().apply {
                    putString(RECIPE_ID, recipeId)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            recipeId = it.getString(RECIPE_ID)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val stepsAdapter = StepsAdapter()
        val ingredientsAdapter = IngredientsAdapter()
        val moreInfoButton = binding.moreInfoButton


        recipeId?.let { viewModel.setCurrentRecipeById(it) }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.currentRecipeState.collectLatest {
                if (it.currentRecipe != null) {
                    binding.recipe = it.currentRecipe
                    stepsAdapter.setData(it.currentRecipe.instructions)
                    ingredientsAdapter.setData(it.currentRecipe.ingredients)
                }
                expandTextView(it.isMoreInfoButtonClicked, expandableTextView, moreInfoButton)
            }
        }

        stepsRecycler.layoutManager = LinearLayoutManager(context)
        ingredientsRecycler.layoutManager = LinearLayoutManager(context)

        stepsRecycler.adapter = stepsAdapter
        ingredientsRecycler.adapter = ingredientsAdapter

        moreInfoButton.setOnClickListener {
            viewModel.changeFlag()
        }

        binding.finishButton.setOnClickListener {
            if (recipeId == null) {
                viewModel.resetFlag()
                findNavController().navigate(R.id.action_fullRecipeFragment_to_feedFragment)
            } else {
                requireActivity().supportFragmentManager.popBackStack()
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }

    private fun expandTextView(flag: Boolean, tv: TextView, bt: TextView) {
        if (!flag) {
            tv.maxLines = 2
            bt.text = getString(R.string.more_info)
        } else {
            tv.maxLines = Integer.MAX_VALUE
            bt.text = getString(R.string.hide)
        }
    }
}
