package com.example.kotlinTeam.onBoarding.data.api.model

import com.example.kotlinTeam.onBoarding.domain.model.OnBoardingPage

data class OnBoardingPageDto(
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
) {
    fun toOnBoardingPage(): OnBoardingPage {
        return OnBoardingPage(
            id,
            title,
            titleCoordinates,
            text,
            textCoordinates,
            textBackground,
            circleCoordinates,
            circleRadius,
            image,
            imageCoordinates
        )
    }
}
