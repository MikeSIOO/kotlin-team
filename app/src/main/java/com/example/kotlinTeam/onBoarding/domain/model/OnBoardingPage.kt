package com.example.kotlinTeam.onBoarding.domain.model

data class OnBoardingPage(
    val id: Int,
    val title: Title,
    val text: Text?,
    val shape: Shape?,
    val image: Image?,
) {
    class Title(
        val text: String,
        val marginStart: Int,
        val marginTop: Int,
        val marginEnd: Int,
        val size: Float,
        val gravity: Boolean,
    )

    class Text(
        val text: String,
        val marginStart: Int,
        val marginTop: Int,
        val marginEnd: Int,
        val size: Float,
        val background: String?,
        val gravity: Boolean
    )

    class Shape(
        val positionX: Int,
        val positionY: Int,
        val radius: Int,
        val color: String,
    )

    class Image(
        val image: String,
        val marginStart: Int,
        val marginTop: Int,
        val marginEnd: Int,
    )
}
