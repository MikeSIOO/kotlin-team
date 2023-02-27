package com.example.kotkin_team.testOnboarding.domain

import com.example.kotkin_team.testOnboarding.data.OnboardingFlow

internal interface GetOnboardingUseCase {
    fun getOnboardingFlow() : OnboardingFlow
}