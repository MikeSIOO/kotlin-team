package com.example.kotkin_team.testOnboarding.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.kotkin_team.testOnboarding.data.OnboardingPage

internal class OnboardingFragment : Fragment() {
    private lateinit var viewModel: OnboardingViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.state.observe(viewLifecycleOwner) { state ->
            when {
                state.isLoading -> {

                }
                state.error != null -> {

                }

                else -> {
                    state.flow?.pages?.first()?.also { bind(it) }
                }
            }
        }
    }

    private fun bind(onboardingPage: OnboardingPage) {

    }
}