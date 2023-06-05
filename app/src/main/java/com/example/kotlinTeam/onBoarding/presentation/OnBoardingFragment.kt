package com.example.kotlinTeam.onBoarding.presentation

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.setPadding
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.kotlinTeam.MainActivity
import com.example.kotlinTeam.R
import com.example.kotlinTeam.common.presentation.dp
import com.example.kotlinTeam.common.presentation.ptX
import com.example.kotlinTeam.common.presentation.ptY
import com.example.kotlinTeam.common.sharedPrefs.SharedPrefs
import com.example.kotlinTeam.common.viewBinding.viewBinding
import com.example.kotlinTeam.databinding.FragmentOnboardingBinding
import com.example.kotlinTeam.onBoarding.domain.events.OnBoardingEvents
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
internal class OnBoardingFragment : Fragment() {
    companion object {
        fun newInstance() = OnBoardingFragment()
    }

    private val binding by viewBinding(FragmentOnboardingBinding::bind)
    private val viewModel: OnBoardingViewModel by viewModels()
    private lateinit var prefs: SharedPrefs

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_onboarding, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prefs = SharedPrefs(requireContext())

        val title = binding.title
        val text = binding.text
        val circle = binding.circle
        val image = binding.image

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.onBoardingState.collectLatest {
                when {
                    it.isLoading -> {
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

        viewModel.pageState.observe(viewLifecycleOwner) {
            val page = viewModel.pageState.value

            if (page == null) {
                prefs.putIsOnboardingRequired(false)
                (activity as MainActivity).setBottomNavigationVisibility(View.VISIBLE)
                findNavController().navigate(R.id.action_onBoardingFragment_to_actionStorage)
            } else {
                title.text = page.title.text

                title.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                    setMargins(
                        leftMargin,
                        page.title.marginTop.ptY,
                        rightMargin,
                        bottomMargin,
                    )
                }
                title.gravity = if (page.title.gravityCenter) Gravity.CENTER else Gravity.START

                page.text?.let {
                    text.text = page.text.text
                    text.textSize = page.text.size
                    text.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                        setMargins(
                            page.text.marginHorizontal?.dp ?: leftMargin,
                            page.text.marginTop?.dp ?: topMargin,
                            page.text.marginHorizontal?.dp ?: rightMargin,
                            bottomMargin
                        )
                    }
                    if (page.text.padding != null) {
                        text.setPadding(page.text.padding)
                    }
                    if (page.text.background) {
                        text.setBackgroundResource(R.drawable.shape_onboarding)
                    }
                    text.gravity = if (page.text.gravityCenter) Gravity.CENTER else Gravity.START
                }

                page.circle?.let {
                    circle.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                        width = page.circle.size.ptX * 2
                        height = page.circle.size.ptX * 2
                        setMargins(
                            page.circle.positionX.ptX - page.circle.size.ptX,
                            page.circle.positionY.ptY - page.circle.size.ptX,
                            0,
                            0,
                        )
                    }
                }

                page.image?.let {
                    Glide
                        .with(requireContext())
                        .load(page.image.image.toInt())
                        .into(image)
                }
            }
        }
    }
}
