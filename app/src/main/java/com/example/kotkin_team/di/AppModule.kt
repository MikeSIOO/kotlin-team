package com.example.kotkin_team.di

import com.example.kotkin_team.profile.data.data_source.remote.FakeApi
import com.example.kotkin_team.profile.data.repository.ProfileRepositoryImpl
import com.example.kotkin_team.profile.domain.repository.ProfileRepository
import com.example.kotkin_team.profile.domain.use_cases.GetMadeRecipes
import com.example.kotkin_team.profile.domain.use_cases.GetProfile
import com.example.kotkin_team.profile.domain.use_cases.ProfileUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideProfileRepository(api: FakeApi): ProfileRepository {
        return ProfileRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideProfileUseCases(repository: ProfileRepository): ProfileUseCases {
        return  ProfileUseCases(
            getProfile = GetProfile(repository),
            getMadeRecipes = GetMadeRecipes(repository),
        )
    }

    @Provides
    @Singleton
    fun provideFakeApi(): FakeApi {
        return FakeApi()
    }
}