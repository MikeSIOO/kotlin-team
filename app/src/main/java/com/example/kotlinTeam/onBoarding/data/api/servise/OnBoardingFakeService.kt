package com.example.kotlinTeam.onBoarding.data.api.servise

import com.example.kotlinTeam.R
import com.example.kotlinTeam.onBoarding.data.api.model.OnBoardingPageDto
import com.example.kotlinTeam.onBoarding.data.api.model.OnBoardingResponseDto
import kotlinx.coroutines.delay
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OnBoardingFakeService @Inject constructor() {
    suspend fun getOnBoarding(): OnBoardingResponseDto {
        delay(1000L)
        val fakeOnBoardingPageDto = getFakeOnBoarding()
        return OnBoardingResponseDto(
            status = "Success",
            count = fakeOnBoardingPageDto.size,
            pages = fakeOnBoardingPageDto
        )
    }

    companion object {
        fun getFakeOnBoarding(): List<OnBoardingPageDto> {
            return listOf(
                OnBoardingPageDto(
                    0,
                    OnBoardingPageDto.Title(
                        "Привет!",
                        30,
                        400,
                        30,
                        40F,
                        false,
                    ),
                    null,
                    OnBoardingPageDto.Shape(
                        -56,
                        416,
                        124,
                        "#00ff00"
                    ),
                    null
                ),
                OnBoardingPageDto(
                    1,
                    OnBoardingPageDto.Title(
                        "Привет!",
                        30,
                        360,
                        30,
                        40F,
                        false,
                    ),
                    OnBoardingPageDto.Text(
                        "Не знаете, что хотите приготовить на ужин сегодня?",
                        30,
                        410,
                        130,
                        20F,
                        null,
                        false
                    ),
                    OnBoardingPageDto.Shape(
                        52,
                        416,
                        232,
                        "#00ff00"
                    ),
                    null
                ),
                OnBoardingPageDto(
                    2,
                    OnBoardingPageDto.Title(
                        "Привет!",
                        30,
                        360,
                        30,
                        40F,
                        false,
                    ),
                    OnBoardingPageDto.Text(
                        "Не переживайте, мы вам поможем.",
                        30,
                        410,
                        130,
                        20F,
                        null,
                        false,
                    ),
                    OnBoardingPageDto.Shape(
                        190,
                        460,
                        320,
                        "#00ff00"
                    ),
                    null
                ),
                OnBoardingPageDto(
                    3,
                    OnBoardingPageDto.Title(
                        "Привет!",
                        30,
                        360,
                        30,
                        40F,
                        false,
                    ),
                    OnBoardingPageDto.Text(
                        "Не переживайте, мы вам поможем.\nДавайте начнем",
                        30,
                        410,
                        130,
                        20F,
                        null,
                        false,
                    ),
                    OnBoardingPageDto.Shape(
                        308,
                        468,
                        488,
                        "#00ff00"
                    ),
                    null
                ),
                OnBoardingPageDto(
                    4,
                    OnBoardingPageDto.Title(
                        "Привет!",
                        30,
                        360,
                        30,
                        40F,
                        false,
                    ),
                    OnBoardingPageDto.Text(
                        "Не переживайте, мы вам поможем.\nДавайте начнем ->",
                        30,
                        410,
                        130,
                        20F,
                        null,
                        false,
                    ),
                    OnBoardingPageDto.Shape(
                        200,
                        300,
                        400,
                        "#00ff00"
                    ),
                    null
                ),
                OnBoardingPageDto(
                    5,
                    OnBoardingPageDto.Title(
                        "Отлично!",
                        80,
                        180,
                        80,
                        40F,
                        true
                    ),
                    OnBoardingPageDto.Text(
                        "Давайте выберем продукты, которые есть у вас в холодильнике. Это нужно, чтобы мы подсказали акутальный для вас рецепт.",
                        40,
                        240,
                        40,
                        14F,
                        "#FFF5ED",
                        true,
                    ),
                    OnBoardingPageDto.Shape(
                        0,
                        0,
                        0,
                        "#00ff00"
                    ),
                    OnBoardingPageDto.Image(
                        R.drawable.ic_launcher_background.toString(),
                        50,
                        350,
                        50,
                    )
                ),
            )
        }
    }
}
