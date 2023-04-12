package com.example.kotlinTeam.onBoarding.domain.model

data class OnBoardingPage(
    val id: Int,
    val title: Title,
    val text: Text?,
    val circle: Circle?,
    val image: Image?,
) {
    class Title(
        val text: String,
        val marginTop: Int,
        val gravityCenter: Boolean,
    )

    class Text(
        val text: String,
        val marginTop: Int?,
        val marginHorizontal: Int?,
        val size: Float,
        val background: Boolean,
        val padding: Int?,
        val gravityCenter: Boolean
    )

    class Circle(
        val positionX: Int,
        val positionY: Int,
        val size: Int,
    )

    class Image(
        val image: String,
    )
}
