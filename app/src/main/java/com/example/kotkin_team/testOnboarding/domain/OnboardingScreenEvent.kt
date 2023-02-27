package com.example.kotkin_team.testOnboarding.domain

internal sealed interface OnboardingScreenEvent {
    object OnNextClick : OnboardingScreenEvent
    data class OnPageSelect(val id: String) : OnboardingScreenEvent
}