package com.example.kotlinTeam.storage.di

import com.example.kotlinTeam.storage.domain.repository.StorageRepository
import com.example.kotlinTeam.storage.domain.repository.StorageRepositoryImplementation
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class StorageRepositoryModule {

    @Binds
    abstract fun bindStorageRepository(storageRepository: StorageRepositoryImplementation): StorageRepository
}
