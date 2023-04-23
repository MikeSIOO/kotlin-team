package com.example.kotlinTeam.feed.presentation

import android.os.Bundle
import android.view.View
import android.view.ViewGroup.MarginLayoutParams
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.kotlinTeam.MainActivity
import com.example.kotlinTeam.R
import com.example.kotlinTeam.common.viewBinding.viewBinding
import com.example.kotlinTeam.databinding.FragmentMatchBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MatchFragment : Fragment(R.layout.fragment_match) {

    private val binding by viewBinding(FragmentMatchBinding::bind)
    private val viewModel: FeedViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.currentRecipeState.collectLatest {
                binding.recipe = it.currentRecipe
            }
        }
        (activity as MainActivity).setBottomNavigationVisibility(View.GONE)
        setUpView()
        super.onViewCreated(view, savedInstanceState)
        val backButton = binding.backToFeedButton
        val startButton = binding.startButton

        backButton.setOnClickListener {
            viewModel.setCurrentRecipe(null)
            navigateToFragment(R.id.action_matchFragment_to_feedFragment)
        }

        startButton.setOnClickListener {
            navigateToFragment(R.id.action_matchFragment_to_fullRecipeFragment)
        }
    }

    override fun onDestroyView() {
        (activity as MainActivity).setBottomNavigationVisibility(View.VISIBLE)
        super.onDestroyView()
    }

    private fun navigateToFragment(fragmentId: Int) {
        findNavController().navigate(fragmentId)
    }

    private fun setUpView() {
        val displayHeight = context?.resources?.displayMetrics?.heightPixels
        val displayWidth = context?.resources?.displayMetrics?.widthPixels
        val density = context?.resources?.displayMetrics?.density

        if ((displayWidth != null) && (displayHeight != null)) {
            if (displayHeight <= SMALL_SCREEN_SIZE) {
                HEIGHT_COEFFICIENT = HEIGHT_COEFFICIENT_SMALL
                binding.letsStartText.visibility = View.GONE
                binding.matchText.textSize = SMALL_TEXT
                binding.backToFeedButton.layoutParams.height =
                    (SMALL_BUTTON_HEIGHT_COEFFICIENT * density!!).toInt()
                binding.backToFeedButton.layoutParams.width =
                    ((displayWidth * SMALL_BUTTON_WIDTH_COEFFICIENT).toInt())
                binding.backToFeedButton.textSize = SMALL_BUTTON_TEXT
                binding.startButton.layoutParams.height =
                    (SMALL_BUTTON_HEIGHT_COEFFICIENT * density).toInt()
                binding.startButton.layoutParams.width =
                    ((displayWidth * SMALL_BUTTON_WIDTH_COEFFICIENT).toInt())
                binding.startButton.textSize = SMALL_BUTTON_TEXT
                val params = binding.matchText.layoutParams as (MarginLayoutParams)
                params.topMargin = ZERO_MARGIN
            }
            binding.userImg.layoutParams.height = (displayHeight / HEIGHT_COEFFICIENT).toInt()
            binding.recipeImg.layoutParams.height = (displayHeight / HEIGHT_COEFFICIENT).toInt()
            binding.userImg.layoutParams.width =
                (binding.userImg.layoutParams.height / WIDTH_COEFFICIENT).toInt()
            binding.recipeImg.layoutParams.width =
                (binding.recipeImg.layoutParams.height / WIDTH_COEFFICIENT).toInt()

            binding.userCard.layoutParams.height =
                (binding.userImg.layoutParams.height + (USER_CARD_HEIGHT_COEFFICIENT * density!!)).toInt()
            binding.dishCard.layoutParams.height =
                (binding.recipeImg.layoutParams.height + (DISH_CARD_HEIGHT_COEFFICIENT * density)).toInt()

            binding.userCard.layoutParams.width =
                (binding.userImg.layoutParams.width + (CARD_WIDTH_COEFFICIENT * density)).toInt()
            binding.dishCard.layoutParams.width =
                (binding.recipeImg.layoutParams.width + (CARD_WIDTH_COEFFICIENT * density)).toInt()
        }
    }

    companion object {
        private var HEIGHT_COEFFICIENT = 3.0
        private const val HEIGHT_COEFFICIENT_SMALL = 3.5
        private const val WIDTH_COEFFICIENT = 1.3636
        private const val SMALL_BUTTON_HEIGHT_COEFFICIENT = 50
        private const val SMALL_BUTTON_WIDTH_COEFFICIENT = 0.7
        private const val SMALL_SCREEN_SIZE = 1000
        private const val SMALL_TEXT = 20F
        private const val SMALL_BUTTON_TEXT = 12F
        private const val ZERO_MARGIN = 0
        private const val USER_CARD_HEIGHT_COEFFICIENT = 50
        private const val DISH_CARD_HEIGHT_COEFFICIENT = 30
        private const val CARD_WIDTH_COEFFICIENT = 44
    }
}
