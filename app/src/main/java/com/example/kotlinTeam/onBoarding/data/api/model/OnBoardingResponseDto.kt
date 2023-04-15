package com.example.kotlinTeam.onBoarding.data.api.model

data class OnBoardingResponseDto(
    val status: String,
    val count: Int,
    val pages: List<OnBoardingPageDto>
)
