package com.example.kotkin_team.di

import com.example.kotkin_team.storage.data.api.service.StorageFakeService
import com.example.kotkin_team.storage.data.db.service.StorageProductDao
import com.example.kotkin_team.storage.data.mapper.StorageCategoryMapper
import com.example.kotkin_team.storage.data.mapper.StorageProductMapper
import com.example.kotkin_team.storage.data.repository.StorageRepositoryImplementation
import com.example.kotkin_team.storage.domain.repository.StorageRepository
import com.example.kotkin_team.storage.domain.use_cases.StorageGetCategory
import com.example.kotkin_team.storage.domain.use_cases.StorageGetProduct
import com.example.kotkin_team.storage.domain.use_cases.StorageSelectProduct
import com.example.kotkin_team.storage.domain.use_cases.StorageUseCases
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
    fun provideRepository(
        storageApiService: StorageFakeService,
//        storageProductDao: StorageProductDao,
        storageCategoryMapper: StorageCategoryMapper,
        storageProductMapper: StorageProductMapper,
    ): StorageRepository {
        return StorageRepositoryImplementation(
            storageApiService,
//            storageProductDao,
            storageCategoryMapper,
            storageProductMapper
        )
    }

    @Provides
    @Singleton
    fun provideUseCases(storageRepository: StorageRepository): StorageUseCases {
        return StorageUseCases(
            storageGetCategory = StorageGetCategory(storageRepository),
            storageGetProduct = StorageGetProduct(storageRepository),
            storageSelectProduct = StorageSelectProduct(storageRepository)
        )
    }

    @Provides
    @Singleton
    fun provideApiService(): StorageFakeService {
        return StorageFakeService()
    }

//    @Provides
//    @Singleton
//    fun provideDatabaseService(): StorageProductDao {
//        return StorageProductDao()
//    }

    @Provides
    @Singleton
    fun provideCategoryMapper(): StorageCategoryMapper {
        return StorageCategoryMapper()
    }

    @Provides
    @Singleton
    fun provideProductMapper(): StorageProductMapper {
        return StorageProductMapper()
    }
}