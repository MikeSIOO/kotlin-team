package com.example.kotlinTeam.profile.di

import com.example.kotlinTeam.profile.data.repository.ProfileRepositoryImpl
import com.example.kotlinTeam.profile.domain.repository.ProfileRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ProfileRepositoryModule {

    @Binds
    abstract fun bindProfileRepository(profileRepository: ProfileRepositoryImpl): ProfileRepository
}
