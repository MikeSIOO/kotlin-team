package com.example.kotlinTeam.onBoarding.domain.state

import com.example.kotlinTeam.onBoarding.domain.model.OnBoardingPage

data class OnBoardingState(
    val isLoading: Boolean = false,
    val onBoardingPage: List<OnBoardingPage> = emptyList(),
    val error: String = ""
)
