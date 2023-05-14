package com.example.kotlinTeam.common.di

import com.example.kotlinTeam.common.data.repository.AuthRepository
import com.example.kotlinTeam.common.data.repository.BaseAuthRepository
import com.example.kotlinTeam.common.data.repository.firebase.BaseAuthenticator
import com.example.kotlinTeam.common.data.repository.firebase.FirebaseAuthenticator
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirestoreModule {

    @Singleton
    @Provides
    fun provideFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Singleton
    @Provides
    fun provideAuthenticator() : BaseAuthenticator {
        return  FirebaseAuthenticator()
    }

    @Singleton
    @Provides
    fun provideRepository(authenticator : BaseAuthenticator) : BaseAuthRepository {
        return AuthRepository(authenticator)
    }
}
