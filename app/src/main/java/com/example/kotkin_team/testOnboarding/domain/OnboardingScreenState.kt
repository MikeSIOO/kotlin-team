package com.example.kotkin_team.testOnboarding.domain

import com.example.kotkin_team.testOnboarding.data.OnboardingFlow

internal data class OnboardingScreenState(
    val isLoading: Boolean,
    val error: Throwable?,
    val currentPage: Int,
    val flow: OnboardingFlow?
)