package com.example.kotlinTeam.onBoarding.data.api.servise

import com.example.kotlinTeam.R
import com.example.kotlinTeam.onBoarding.data.api.model.OnBoardingPageDto
import com.example.kotlinTeam.onBoarding.data.api.model.OnBoardingResponseDto
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.delay

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
        private fun getX(X: Int, W: Int) = ((W.toFloat() / 2 + X) / 360 * 100).toInt()
        private fun getY(Y: Int, W: Int) = ((W.toFloat() / 2 + Y) / 800 * 100).toInt()
        private fun getR(W: Int) = ((W.toFloat() / 2) / 360 * 100).toInt()
        fun getFakeOnBoarding(): List<OnBoardingPageDto> {
            return listOf(
                OnBoardingPageDto(
                    0,
                    OnBoardingPageDto.Title(
                        "Привет!",
                        75,
                        false,
                    ),
                    null,
                    OnBoardingPageDto.Circle(
                        getX(-179, 251),
                        getY(491, 251),
                        getR(251),
                    ),
                    null
                ),
                OnBoardingPageDto(
                    1,
                    OnBoardingPageDto.Title(
                        "Привет!",
                        69,
                        false,
                    ),
                    OnBoardingPageDto.Text(
                        "Не знаете, что\nхотите приготовить\nна ужин сегодня?",
                        null,
                        null,
                        20F,
                        false,
                        null,
                        false,
                    ),
                    OnBoardingPageDto.Circle(
                        getX(-179, 465),
                        getY(383, 465),
                        getR(465),
                    ),
                    null
                ),
                OnBoardingPageDto(
                    2,
                    OnBoardingPageDto.Title(
                        "Привет!",
                        69,
                        false,
                    ),
                    OnBoardingPageDto.Text(
                        "Не переживайте, мы\nвам поможем.",
                        null,
                        null,
                        20F,
                        false,
                        null,
                        false,
                    ),
                    OnBoardingPageDto.Circle(
                        getX(-131, 640),
                        getY(346, 640),
                        getR(640),
                    ),
                    null
                ),
                OnBoardingPageDto(
                    3,
                    OnBoardingPageDto.Title(
                        "Привет!",
                        68,
                        false,
                    ),
                    OnBoardingPageDto.Text(
                        "Не переживайте, мы\nвам поможем.\nДавайте начнем",
                        null,
                        null,
                        20F,
                        false,
                        null,
                        false,
                    ),
                    OnBoardingPageDto.Circle(
                        getX(-179, 977),
                        getY(177, 977),
                        getR(977),
                    ),
                    null
                ),
                OnBoardingPageDto(
                    4,
                    OnBoardingPageDto.Title(
                        "Привет!",
                        68,
                        false,
                    ),
                    OnBoardingPageDto.Text(
                        "Не переживайте, мы\nвам поможем.\nДавайте начнем ⟶",
                        null,
                        null,
                        20F,
                        false,
                        null,
                        false,
                    ),
                    OnBoardingPageDto.Circle(
                        getX(-382, 1281),
                        getY(-127, 1281),
                        getR(1281),
                    ),
                    null
                ),
                OnBoardingPageDto(
                    5,
                    OnBoardingPageDto.Title(
                        "Отлично!",
                        27,
                        true,
                    ),
                    OnBoardingPageDto.Text(
                        "Давайте выберем продукты, которые есть у вас в холодильнике. Это нужно, чтобы мы подсказали акутальный для вас рецепт.",
                        16,
                        50,
                        12F,
                        true,
                        30,
                        true,
                    ),
                    OnBoardingPageDto.Circle(
                        0,
                        0,
                        0,
                    ),
                    OnBoardingPageDto.Image(
                        R.drawable.onboarding.toString()
                    )
                ),
            )
        }
    }
}
