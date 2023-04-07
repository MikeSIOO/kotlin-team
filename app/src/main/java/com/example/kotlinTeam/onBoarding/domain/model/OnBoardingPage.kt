package com.example.kotlinTeam.onBoarding.domain.model

data class OnBoardingPage(
    val id: Int,
    val title: String,
    val titleCoordinates: Pair<Int, Int>,
    val text: String,
    val textCoordinates: Pair<Int, Int>,
    val textBackground: Boolean,
    val circleCoordinates: Pair<Int, Int>?,
    val circleRadius: Int?,
    val image: String?,
    val imageCoordinates: Pair<Int, Int>?,
)
