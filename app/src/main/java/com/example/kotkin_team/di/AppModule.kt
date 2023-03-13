package com.example.kotkin_team.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

//    @Provides
//    @Singleton
//    fun provideProfileRepository(api: FakeApi): ProfileRepository {
//        return ProfileRepositoryImpl(api)
//    }
//
//    @Provides
//    @Singleton
//    fun provideProfileUseCases(repository: ProfileRepository): ProfileUseCases {
//        return  ProfileUseCases(
//            getProfile = GetProfile(repository),
//            getMadeRecipes = GetMadeRecipes(repository),
//        )
//    }
//
//    @Provides
//    @Singleton
//    fun provideFakeApi(): FakeApi {
//        return FakeApi()
//    }
}