package com.example.kotlinTeam.onBoarding.domain.events

sealed class OnBoardingEvents {
    object NextPage : OnBoardingEvents()
}
