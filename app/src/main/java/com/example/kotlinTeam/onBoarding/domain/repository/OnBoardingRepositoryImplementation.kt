package com.example.kotlinTeam.onBoarding.domain.repository

import com.example.kotlinTeam.onBoarding.common.OnBoardingStatuses
import com.example.kotlinTeam.onBoarding.data.api.servise.OnBoardingFakeService
import com.example.kotlinTeam.onBoarding.domain.model.OnBoardingPage
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

// репозиторий (запрос к сервису и преобразование ответа в модель приложения)
@Singleton
class OnBoardingRepositoryImplementation @Inject constructor(
    private val onBoardingApiService: OnBoardingFakeService,
) : OnBoardingRepository {
    override fun getPage(): Flow<OnBoardingStatuses<List<OnBoardingPage>>> = flow {
        try {
            emit(OnBoardingStatuses.Loading())
            val storageCategoryDto =
                onBoardingApiService.getOnBoarding().pages.map { it.toOnBoardingPage() }
            emit(OnBoardingStatuses.Success(storageCategoryDto))
        } catch (e: IOException) {
            emit(
                OnBoardingStatuses.Error(
                    "Не обнаружено соединение с сервером. Проверьте интернет подключение"
                )
            )
        }
    }
}
