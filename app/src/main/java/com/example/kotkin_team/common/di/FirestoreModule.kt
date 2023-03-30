package com.example.kotkin_team.common.di

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

//    @Provides
//    fun provideFirestoreRepository(
//        firestore: FirebaseFirestore
//    ): FirestoreRepository {
//        return FirestoreRepository(firestore)
//    }
}
