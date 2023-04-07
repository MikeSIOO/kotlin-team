package com.example.kotlinTeam.onBoarding.data.api.servise

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
                    "Привет!",
                    Pair(10, 10),
                    "Empty text",
                    Pair(10, 10),
                    false,
                    Pair(10, 10),
                    10,
                    null,
                    null
                ),
                OnBoardingPageDto(
                    1,
                    "Привет!",
                    Pair(30, 30),
                    "Не знаете, что хотите приготовить на ужин сегодня?",
                    Pair(30, 30),
                    false,
                    Pair(30, 30),
                    30,
                    null,
                    null
                ),
                OnBoardingPageDto(
                    2,
                    "Привет!",
                    Pair(50, 50),
                    "Не переживайте, мы вам поможем.",
                    Pair(50, 50),
                    false,
                    Pair(50, 50),
                    50,
                    null,
                    null
                ),
                OnBoardingPageDto(
                    3,
                    "Привет!",
                    Pair(70, 70),
                    "Не переживайте, мы вам поможем.\nДавайте начнем",
                    Pair(70, 70),
                    false,
                    Pair(70, 70),
                    70,
                    null,
                    null
                ),
                OnBoardingPageDto(
                    4,
                    "Отлично!",
                    Pair(90, 90),
                    "Давайте выберем продукты, которые есть у вас в холодильнике. Это нужно, чтобы мы подсказали акутальный для вас рецепт.",
                    Pair(90, 90),
                    true,
                    null,
                    null,
                    "TODO image",
                    Pair(90, 90),
                ),
                // TODO image
            )
        }
    }
}
