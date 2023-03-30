package com.example.kotkin_team.common.di

import android.app.Application
import com.example.kotkin_team.common.data.repository.FirestoreRepositoryImpl
import com.example.kotkin_team.profile.domain.repository.ProfileRepository
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object FirestoreModule {

    @Provides
    fun provideFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Provides
    fun provideProfileRepository(
        firestore: FirebaseFirestore,
        appContext: Application
    ): ProfileRepository {
        return FirestoreRepositoryImpl(firestore, appContext)
    }
}
