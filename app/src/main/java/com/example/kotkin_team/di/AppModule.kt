package com.example.kotkin_team.di

import com.example.kotkin_team.products.data.fake.ProductsFakeService
import com.example.kotkin_team.products.data.repository.ProductsRepositoryImplementation
import com.example.kotkin_team.products.domain.repository.ProductsRepository
import com.example.kotkin_team.products.domain.use_cases.ProductsGetCategory
import com.example.kotkin_team.products.domain.use_cases.ProductsUseCases
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
    fun provideRepository(api: ProductsFakeService): ProductsRepository {
        return ProductsRepositoryImplementation(api)
    }

    @Provides
    @Singleton
    fun provideUseCases(productsRepository: ProductsRepository): ProductsUseCases {
        return  ProductsUseCases(
            productsGetCategory = ProductsGetCategory(productsRepository)
        )
    }

    @Provides
    @Singleton
    fun provideService(): ProductsFakeService {
        return ProductsFakeService()
    }
}