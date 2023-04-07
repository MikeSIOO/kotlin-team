package com.example.kotlinTeam.onBoarding.data.api.model

import com.example.kotlinTeam.onBoarding.domain.model.OnBoardingPage

data class OnBoardingPageDto(
    val id: Int,
    val title: Title,
    val text: Text?,
    val shape: Shape?,
    val image: Image?,
) {
    fun toOnBoardingPage(): OnBoardingPage {
        return OnBoardingPage(
            id,
            OnBoardingPage.Title(
                title.text,
                title.marginStart,
                title.marginTop,
                title.marginEnd,
                title.size,
                title.gravity,
            ),
            text?.let {
                OnBoardingPage.Text(
                    text.text,
                    text.marginStart,
                    text.marginTop,
                    text.marginEnd,
                    text.size,
                    text.background,
                    text.gravity
                )
            },
            shape?.let {
                OnBoardingPage.Shape(
                    shape.positionX,
                    shape.positionY,
                    shape.radius,
                    shape.color,
                )
            },
            image?.let {
                OnBoardingPage.Image(
                    image.image,
                    image.marginStart,
                    image.marginTop,
                    image.marginEnd,
                )
            }
        )
    }

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
        val gravity: Boolean,
    )

    class Shape(
        val positionX: Int,
        val positionY: Int,
        val radius: Int,
        val color: String
    )

    class Image(
        val image: String,
        val marginStart: Int,
        val marginTop: Int,
        val marginEnd: Int,
    )
}
