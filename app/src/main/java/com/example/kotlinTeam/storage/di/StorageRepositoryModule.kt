package com.example.kotlinTeam.storage.di

import android.content.Context
import androidx.room.Room
import com.example.kotlinTeam.storage.data.db.database.StorageProductDatabase
import com.example.kotlinTeam.storage.data.db.service.StorageProductDao
import com.example.kotlinTeam.storage.domain.repository.StorageRepository
import com.example.kotlinTeam.storage.domain.repository.StorageRepositoryImplementation
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class StorageRepositoryModule {

    @Binds
    abstract fun bindStorageRepository(storageRepository: StorageRepositoryImplementation): StorageRepository
}

@Module
@InstallIn(SingletonComponent::class)
object ProductModule {
    @Provides
    @Singleton
    fun provideProductDatabase(@ApplicationContext appContext: Context): StorageProductDatabase {
        return Room.databaseBuilder(
            appContext,
            StorageProductDatabase::class.java,
            "StorageProduct"
        ).build()
    }

    @Provides
    @Singleton
    fun provideProductDao(storageProductDatabase: StorageProductDatabase): StorageProductDao {
        return storageProductDatabase.storageProductDao
    }
}
