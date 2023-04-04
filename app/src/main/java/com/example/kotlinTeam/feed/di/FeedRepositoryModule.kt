package com.example.kotlinTeam.feed.di

import com.example.kotlinTeam.feed.data.FeedRepositoryImpl
import com.example.kotlinTeam.feed.domain.repository.FeedRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class FeedRepositoryModule {

    @Binds
    abstract fun bindFeedRepository(feedRepository: FeedRepositoryImpl): FeedRepository
}
