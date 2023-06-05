package com.example.kotlinTeam.onBoarding.domain.repository

import com.example.kotlinTeam.onBoarding.common.OnBoardingStatuses
import com.example.kotlinTeam.onBoarding.domain.model.OnBoardingPage
import kotlinx.coroutines.flow.Flow

interface OnBoardingRepository {
    fun getPage(): Flow<OnBoardingStatuses<List<OnBoardingPage>>>
}
