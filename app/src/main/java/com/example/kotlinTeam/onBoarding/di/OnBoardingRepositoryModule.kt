package com.example.kotlinTeam.onBoarding.di

import com.example.kotlinTeam.onBoarding.domain.repository.OnBoardingRepository
import com.example.kotlinTeam.onBoarding.domain.repository.OnBoardingRepositoryImplementation
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class OnBoardingRepositoryModule {

    @Binds
    abstract fun bindOnBoardingRepository(
        onBoardingRepository: OnBoardingRepositoryImplementation
    ): OnBoardingRepository
}
