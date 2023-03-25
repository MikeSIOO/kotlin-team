package com.example.kotkin_team.di

import com.example.kotkin_team.feed.data.remote.FakeApi
import com.example.kotkin_team.feed.data.FeedRepositoryImpl
import com.example.kotkin_team.feed.domain.repository.FeedRepository
import com.example.kotkin_team.feed.domain.GetFeedUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

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

    @Provides
    @Singleton
    fun provideFeedRepository(fakeApi: FakeApi): FeedRepository {
        return FeedRepositoryImpl(fakeApi)
    }

    @Provides
    @Singleton
    fun provideGetFeedUseCase(repository: FeedRepository): GetFeedUseCase {
        return GetFeedUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideFakeApi(): FakeApi {
        return FakeApi()
    }
}
