package com.example.kotlinTeam.common.di

import android.content.Context
import androidx.room.Room
import com.example.kotlinTeam.storage.data.db.database.StorageProductDatabase
import com.example.kotlinTeam.storage.data.db.service.StorageProductDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
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
