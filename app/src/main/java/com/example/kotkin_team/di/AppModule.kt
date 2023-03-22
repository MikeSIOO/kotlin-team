package com.example.kotkin_team.di

import com.example.kotkin_team.storage.data.api.service.StorageFakeService
import com.example.kotkin_team.storage.data.mapper.StorageCategoryMapper
import com.example.kotkin_team.storage.data.mapper.StorageProductMapper
import com.example.kotkin_team.storage.data.repository.StorageRepositoryImplementation
import com.example.kotkin_team.storage.domain.repository.StorageRepository
import com.example.kotkin_team.storage.domain.use_cases.StorageGetCategory
import com.example.kotkin_team.storage.domain.use_cases.StorageGetProduct
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
        service: StorageFakeService,
        storageCategoryMapper: StorageCategoryMapper,
        storageProductMapper: StorageProductMapper,
    ): StorageRepository {
        return StorageRepositoryImplementation(service, storageCategoryMapper, storageProductMapper)
    }

    @Provides
    @Singleton
    fun provideUseCases(storageRepository: StorageRepository): StorageUseCases {
        return StorageUseCases(
            storageGetCategory = StorageGetCategory(storageRepository),
            storageGetProduct = StorageGetProduct(storageRepository)
        )
    }

    @Provides
    @Singleton
    fun provideService(): StorageFakeService {
        return StorageFakeService()
    }

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