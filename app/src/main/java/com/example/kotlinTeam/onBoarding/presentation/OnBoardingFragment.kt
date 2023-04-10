package com.example.kotlinTeam.onBoarding.presentation

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.kotlinTeam.R
import com.example.kotlinTeam.common.viewBinding.viewBinding
import com.example.kotlinTeam.databinding.FragmentOnboardingBinding
import com.example.kotlinTeam.onBoarding.domain.events.OnBoardingEvents
import com.example.kotlinTeam.common.presentation.dp
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
internal class OnBoardingFragment : Fragment() {
    companion object {
        fun newInstance() = OnBoardingFragment()
    }

    private val binding by viewBinding(FragmentOnboardingBinding::bind)
    private val viewModel: OnBoardingViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_onboarding, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val title = binding.title
        val text = binding.text
        val circle = binding.circle
        val image = binding.image

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.onBoardingState.collectLatest {
                when {
                    it.isLoading -> {
                        Toast.makeText(context, "LOADING...", Toast.LENGTH_SHORT).show()
                    }
                    it.error.isNotBlank() -> {
                        Toast.makeText(context, it.error, Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        binding.onBoarding.setOnClickListener {
                            viewModel.onEvent(OnBoardingEvents.NextPage)
                        }
                    }
                }
            }
        }

        viewModel.index.observe(viewLifecycleOwner) {
            if (viewModel.index.value!! >= viewModel.onBoardingState.value.onBoardingPage.size) {
                // TODO перейти к продуктам
                Toast.makeText(context, "TODO Переход к продуктам", Toast.LENGTH_SHORT).show()
            } else if (viewModel.index.value!! != -1) {
                val page = viewModel.onBoardingState.value.onBoardingPage[viewModel.index.value!!]

                title.text = page.title.text
                title.textSize = page.title.size
                title.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                    setMargins(
                        page.title.marginStart.dp,
                        page.title.marginTop.dp,
                        page.title.marginEnd.dp,
                        0
                    )
                }
                if (page.title.gravity) title.gravity = Gravity.CENTER

                page.text?.let {
                    text.text = page.text.text
                    text.textSize = page.text.size
                    text.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                        setMargins(
                            page.text.marginStart.dp,
                            page.text.marginTop.dp,
                            page.text.marginEnd.dp,
                            0
                        )
                    }
                    if (page.text.background != null) {
                        text.setBackgroundColor(Color.parseColor(page.text.background))
                        text.background
                    }
                    if (page.text.gravity) text.gravity = Gravity.CENTER
                }

                page.shape?.let {
                    circle.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                        width = (page.shape.radius * 2).dp
                        height = (page.shape.radius * 2).dp
                        setMargins(
                            (page.shape.positionX - page.shape.radius).dp,
                            (page.shape.positionY - page.shape.radius).dp,
                            (page.shape.positionX + page.shape.radius).dp,
                            (page.shape.positionY + page.shape.radius).dp,
                        )
                    }
                }

                page.image?.let {
                    Glide
                        .with(requireContext())
                        .load(page.image.image.toInt())
                        .into(binding.image)
                    image.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                        setMargins(
                            page.image.marginStart.dp,
                            page.image.marginTop.dp,
                            page.image.marginEnd.dp,
                            0
                        )
                    }
                }
            }
        }
    }
}
