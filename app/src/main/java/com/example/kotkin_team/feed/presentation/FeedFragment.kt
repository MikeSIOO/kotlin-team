package com.example.kotkin_team.feed.presentation

import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.view.animation.LinearInterpolator
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import com.example.kotkin_team.R
import com.example.kotkin_team.databinding.FragmentFeedBinding
import com.example.kotkin_team.feed.common.viewBinding
import com.example.kotkin_team.feed.domain.FeedAdapter
import com.example.kotkin_team.feed.domain.FeedLoadingState
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.Direction
import com.yuyakaido.android.cardstackview.Duration
import com.yuyakaido.android.cardstackview.RewindAnimationSetting
import com.yuyakaido.android.cardstackview.StackFrom
import com.yuyakaido.android.cardstackview.SwipeAnimationSetting
import com.yuyakaido.android.cardstackview.SwipeableMethod
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeedFragment : Fragment(R.layout.fragment_feed), CardStackListener {
    private val binding by viewBinding(FragmentFeedBinding::bind)
    private val manager by lazy { CardStackLayoutManager(context, this) }
    private val myAdapter = FeedAdapter()
    private val viewModel: FeedViewModel by activityViewModels()
    private val cardStackView get() = binding.cardStackView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val progressBar = binding.progressBar

        viewModel.feedState.observe(
            viewLifecycleOwner
        ) { result ->
            myAdapter.submitList(result.recipeList)
            if (result.loadingState == FeedLoadingState.LOADING) {
                progressBar.visibility = View.VISIBLE
            } else if (result.loadingState == FeedLoadingState.LOADED) {
                progressBar.visibility = View.GONE
            }
        }
        setupCardStackView()
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

    private fun setupCardStackView() {
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
            adapter = myAdapter
        }
    }

    override fun onCardDragging(direction: Direction?, ratio: Float) {

    }

    override fun onCardSwiped(direction: Direction?) {
        if (direction == Direction.Right) {
            val recipe = myAdapter.currentList[manager.topPosition - 1]
            viewModel.setCurrentRecipe(recipe)
            activity?.supportFragmentManager?.let {
                val transaction = it.beginTransaction()
                val matchFragment = MatchFragment.newInstance(recipe.id)
                transaction
                    .replace(R.id.fragmentContainer, matchFragment)
                    .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                    .addToBackStack(null)
                    .commit()
            }
        }
    }

    override fun onCardRewound() {

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
