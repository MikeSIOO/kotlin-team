package com.example.kotkin_team.di

import com.example.kotkin_team.products.data.fake.Service
import com.example.kotkin_team.products.data.repository.RepositoryImplementation
import com.example.kotkin_team.products.domain.repository.Repository
import com.example.kotkin_team.products.domain.use_cases.GetCategory
import com.example.kotkin_team.products.domain.use_cases.UseCases
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
    fun provideProfileRepository(api: Service): Repository {
        return RepositoryImplementation(api)
    }

    @Provides
    @Singleton
    fun provideProfileUseCases(repository: Repository): UseCases {
        return  UseCases(
            getCategory = GetCategory(repository)
        )
    }

    @Provides
    @Singleton
    fun provideFakeApi(): Service {
        return Service()
    }
}