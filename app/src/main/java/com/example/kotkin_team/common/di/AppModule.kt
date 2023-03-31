package com.example.kotkin_team.common.di

import android.content.Context
import androidx.room.Room
import com.example.kotkin_team.storage.data.api.service.StorageFakeService
import com.example.kotkin_team.storage.data.db.database.StorageProductDatabase
import com.example.kotkin_team.storage.data.db.service.StorageProductDao
import com.example.kotkin_team.storage.data.mapper.StorageCategoryMapper
import com.example.kotkin_team.storage.data.mapper.StorageProductMapper
import com.example.kotkin_team.storage.domain.repository.StorageRepositoryImplementation
import com.example.kotkin_team.storage.domain.repository.StorageRepository
import com.example.kotkin_team.storage.domain.use_cases.StorageGetCategoryUseCase
import com.example.kotkin_team.storage.domain.use_cases.StorageGetProductUseCase
import com.example.kotkin_team.storage.domain.use_cases.StorageSelectProductUseCase
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
    fun provideRepository(
        storageApiService: StorageFakeService,
        storageProductDao: StorageProductDao,
        storageCategoryMapper: StorageCategoryMapper,
        storageProductMapper: StorageProductMapper,
    ): StorageRepository {
        return StorageRepositoryImplementation(
            storageApiService,
            storageProductDao,
            storageCategoryMapper,
            storageProductMapper
        )
    }

    @Provides
    @Singleton
    fun provideStorageGetCategoryUseCase(storageRepository: StorageRepository): StorageGetCategoryUseCase {
        return StorageGetCategoryUseCase(storageRepository)
    }

    @Provides
    @Singleton
    fun provideStorageGetProductUseCase(storageRepository: StorageRepository): StorageGetProductUseCase {
        return StorageGetProductUseCase(storageRepository)
    }

    @Provides
    @Singleton
    fun provideStorageSelectProductUseCase(storageRepository: StorageRepository): StorageSelectProductUseCase {
        return StorageSelectProductUseCase(storageRepository)
    }

    @Provides
    @Singleton
    fun provideApiService(): StorageFakeService {
        return StorageFakeService()
    }

    @Provides
    @Singleton
    fun provideProductDao(storageProductDatabase: StorageProductDatabase): StorageProductDao {
        return storageProductDatabase.storageProductDao()
    }

    @Provides
    @Singleton
    fun provideProductDatabase(@ApplicationContext appContext: Context): StorageProductDatabase {
        return Room.databaseBuilder(
            appContext,
            StorageProductDatabase::class.java,
            "StorageProduct"
        ).build()
    }
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
