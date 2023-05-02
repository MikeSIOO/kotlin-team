package com.example.kotlinTeam.feed.presentation

import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.view.animation.LinearInterpolator
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.DefaultItemAnimator
import com.example.kotlinTeam.R
import com.example.kotlinTeam.common.viewBinding.viewBinding
import com.example.kotlinTeam.databinding.FragmentFeedBinding
import com.example.kotlinTeam.feed.domain.FeedAdapter
import com.example.kotlinTeam.feed.domain.FeedLoadStateAdapter
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.Direction
import com.yuyakaido.android.cardstackview.Duration
import com.yuyakaido.android.cardstackview.RewindAnimationSetting
import com.yuyakaido.android.cardstackview.StackFrom
import com.yuyakaido.android.cardstackview.SwipeAnimationSetting
import com.yuyakaido.android.cardstackview.SwipeableMethod
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FeedFragment : Fragment(R.layout.fragment_feed), CardStackListener {
    private val binding by viewBinding(FragmentFeedBinding::bind)
    lateinit var manager: CardStackLayoutManager
    private val feedRecipeAdapter = FeedAdapter()
    private val header = FeedLoadStateAdapter()
    private val viewModel: FeedViewModel by activityViewModels()
    private val cardStackView get() = binding.cardStackView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        val adapterWithLoadState = feedRecipeAdapter.withLoadStateHeader(header)
        binding.retryButton.setOnClickListener { feedRecipeAdapter.retry() }
        manager = CardStackLayoutManager(context, this)
        manager.topPosition = viewModel.currentRecipeState.value.topPosition

        setupCardStackView(adapterWithLoadState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.feedRecipes.collect { recipes ->
                recipes.let {
                    feedRecipeAdapter.submitData(it)
                }
            }
        }

        observeLoadState(feedRecipeAdapter)

        setupButtons()
        cardStackView.itemAnimator.apply {
            if (this is DefaultItemAnimator) {
                supportsChangeAnimations = false
            }
        }
    }

    private fun setupButtons() {
        val skip = binding.skipButton
        val like = binding.likeButton
        val rewind = binding.rewindButton

        skip.setOnClickListener {
            val setting = SwipeAnimationSetting.Builder()
                .setDirection(Direction.Left)
                .setDuration(Duration.Normal.duration)
                .setInterpolator(AccelerateInterpolator())
                .build()
            manager.setSwipeAnimationSetting(setting)
            cardStackView.swipe()
        }

        rewind.setOnClickListener {
            val setting = RewindAnimationSetting.Builder()
                .setDirection(Direction.Bottom)
                .setDuration(Duration.Normal.duration)
                .setInterpolator(DecelerateInterpolator())
                .build()
            manager.setRewindAnimationSetting(setting)
            cardStackView.rewind()
        }

        like.setOnClickListener {
            val setting = SwipeAnimationSetting.Builder()
                .setDirection(Direction.Right)
                .setDuration(Duration.Normal.duration)
                .setInterpolator(AccelerateInterpolator())
                .build()
            manager.setSwipeAnimationSetting(setting)
            cardStackView.swipe()
        }
    }

    private fun setupCardStackView(feedAdapter: ConcatAdapter) {
        manager.setStackFrom(StackFrom.None)
        manager.setVisibleCount(VISIBLE_COUNT)
        manager.setTranslationInterval(TRANSLATION_INTERVAL)
        manager.setScaleInterval(SCALE_INTERVAL)
        manager.setSwipeThreshold(SWIPE_THRESHOLD)
        manager.setMaxDegree(MAX_DEGREE)
        manager.setDirections(Direction.HORIZONTAL)
        manager.setCanScrollHorizontal(true)
        manager.setCanScrollVertical(false)
        manager.setSwipeableMethod(SwipeableMethod.AutomaticAndManual)
        manager.setOverlayInterpolator(LinearInterpolator())

        cardStackView.apply {
            layoutManager = manager
            adapter = feedAdapter
        }
    }

    private fun observeLoadState(feedAdapter: FeedAdapter) {
        lifecycleScope.launch {
            feedAdapter.loadStateFlow.collectLatest {
                binding.cardStackViewWrapper.isVisible = it.refresh is LoadState.NotLoading
                binding.progressBar.isVisible = it.refresh is LoadState.Loading
                binding.retryButton.isVisible =
                    it.refresh is LoadState.Error && feedAdapter.itemCount == 0
            }
        }
    }

    override fun onCardDragging(direction: Direction?, ratio: Float) {
    }

    override fun onCardSwiped(direction: Direction?) {
        if (direction == Direction.Right) {
            val recipe = feedRecipeAdapter.snapshot().items[manager.topPosition - 1]
            viewModel.changeManagerTopPosition(1)
            viewModel.setCurrentRecipe(recipe)
            findNavController().navigate(R.id.action_feedFragment_to_matchFragment)
        }
    }

    override fun onCardRewound() {
        viewModel.changeManagerTopPosition(-1)
    }

    override fun onCardCanceled() {
    }

    override fun onCardAppeared(view: View?, position: Int) {
    }

    override fun onCardDisappeared(view: View?, position: Int) {
    }

    companion object {
        const val VISIBLE_COUNT = 3
        const val TRANSLATION_INTERVAL = 98.0f
        const val SCALE_INTERVAL = 0.95f
        const val SWIPE_THRESHOLD = 0.3f
        const val MAX_DEGREE = 20.0f
    }
}
