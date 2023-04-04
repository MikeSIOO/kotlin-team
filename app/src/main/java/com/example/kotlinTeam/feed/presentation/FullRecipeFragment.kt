package com.example.kotlinTeam.feed.presentation

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinTeam.R
import com.example.kotlinTeam.common.viewBinding.viewBinding
import com.example.kotlinTeam.databinding.FragmentFullRecipeBinding
import com.example.kotlinTeam.feed.domain.IngredientsAdapter
import com.example.kotlinTeam.feed.domain.StepsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FullRecipeFragment : Fragment(R.layout.fragment_full_recipe) {
    private val binding by viewBinding(FragmentFullRecipeBinding::bind)
    private val viewModel: FeedViewModel by activityViewModels()
    private val ingredientsRecycler get() = binding.ingredientsRecycler
    private val stepsRecycler get() = binding.stepsRecycler
    private val expandableTextView get() = binding.dishInfo

    private var isMoreInfoButtonClicked = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val stepsAdapter = StepsAdapter()
        val ingredientsAdapter = IngredientsAdapter()
        val moreInfoButton = binding.moreInfoButton

        viewModel.feedState.observe(
            viewLifecycleOwner
        ) {
            binding.recipe = it.currentRecipe
            if (it.currentRecipe != null) {
                stepsAdapter.setData(it.currentRecipe.steps)
                ingredientsAdapter.setData(it.currentRecipe.ingredients)
            }

            expandTextView(it.isMoreInfoButtonClicked, expandableTextView, moreInfoButton)
        }

        stepsRecycler.layoutManager = LinearLayoutManager(context)
        ingredientsRecycler.layoutManager = LinearLayoutManager(context)

        stepsRecycler.adapter = stepsAdapter
        ingredientsRecycler.adapter = ingredientsAdapter

        moreInfoButton.setOnClickListener {
            viewModel.setFlag()
//            expandTextView(isMoreInfoButtonClicked, expandableTextView, moreInfoButton)
            isMoreInfoButtonClicked = !isMoreInfoButtonClicked
//            Log.d("test", "current ingredients: ${ingredientsAdapter.ingredients}")
        }

        binding.finishButton.setOnClickListener {
            viewModel.setCurrentRecipe(null)
            activity?.supportFragmentManager?.let {
                val transaction = it.beginTransaction()
                transaction
                    .replace(R.id.fragmentContainer, FeedFragment())
                    .addToBackStack(null)
                    .commit()
            }
        }
    }

    private fun expandTextView(flag: Boolean, tv: TextView, bt: TextView) {
        if (!flag) {
            tv.maxLines = 2
            bt.text = "Подробнее"
        } else {
            tv.maxLines = Integer.MAX_VALUE
            bt.text = "Свернуть"
        }
    }
}
