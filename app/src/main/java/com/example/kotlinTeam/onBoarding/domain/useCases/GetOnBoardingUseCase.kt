package com.example.kotlinTeam.onBoarding.domain.useCases

import com.example.kotlinTeam.onBoarding.common.OnBoardingStatuses
import com.example.kotlinTeam.onBoarding.domain.model.OnBoardingPage
import com.example.kotlinTeam.onBoarding.domain.repository.OnBoardingRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetOnBoardingUseCase @Inject constructor(
    private val onBoardingRepository: OnBoardingRepository
) {
    operator fun invoke(): Flow<OnBoardingStatuses<List<OnBoardingPage>>> {
        return onBoardingRepository.getPage()
    }
}
