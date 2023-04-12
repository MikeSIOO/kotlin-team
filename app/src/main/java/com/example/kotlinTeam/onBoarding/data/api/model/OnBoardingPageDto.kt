package com.example.kotlinTeam.onBoarding.data.api.model

import com.example.kotlinTeam.onBoarding.domain.model.OnBoardingPage

data class OnBoardingPageDto(
    val id: Int,
    val title: Title,
    val text: Text?,
    val circle: Circle?,
    val image: Image?,
) {
    fun toOnBoardingPage(): OnBoardingPage {
        return OnBoardingPage(
            id,
            OnBoardingPage.Title(
                title.text,
                title.marginTop,
                title.gravityCenter,
            ),
            text?.let {
                OnBoardingPage.Text(
                    text.text,
                    text.marginTop,
                    text.marginHorizontal,
                    text.size,
                    text.background,
                    text.padding,
                    text.gravityCenter
                )
            },
            circle?.let {
                OnBoardingPage.Circle(
                    circle.positionX,
                    circle.positionY,
                    circle.size,
                )
            },
            image?.let {
                OnBoardingPage.Image(
                    image.image,
                )
            }
        )
    }

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
