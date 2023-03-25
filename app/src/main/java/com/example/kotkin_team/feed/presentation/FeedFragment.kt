package com.example.kotkin_team.feed.presentation

import android.os.Bundle
import android.util.Log
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

        viewModel.recipes.observe(
            viewLifecycleOwner
        ) { result ->
            myAdapter.submitList(result)
        }
        viewModel.loadingState.observe(
            viewLifecycleOwner
        ) {
            if (it == FeedLoadingState.LOADING) {
                progressBar.visibility = View.VISIBLE
            } else if (it == FeedLoadingState.LOADED) {
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
        manager.setVisibleCount(3)
        manager.setTranslationInterval(98.0f)
        manager.setScaleInterval(0.95f)
        manager.setSwipeThreshold(0.3f)
        manager.setMaxDegree(20.0f)
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
        if (direction != null) {
            Log.d("CardStackView", "onCardDragging: d = ${direction.name}, r = $ratio")
        }
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
        Log.d("CardStackView", "onCardRewound: ${manager.topPosition}")
    }

    override fun onCardCanceled() {
        Log.d("CardStackView", "onCardCanceled: ${manager.topPosition}")
    }

    override fun onCardAppeared(view: View?, position: Int) {
        Log.d("CardStackView", "onCardAppeared: ($position) text")
    }

    override fun onCardDisappeared(view: View?, position: Int) {
        Log.d("CardStackView", "onCardDisappeared: ($position) text")
    }
}
