package com.example.kotlinTeam.authentication.domain

import com.example.kotlinTeam.common.data.repository.AuthRepository
import com.example.kotlinTeam.common.data.repository.FirestoreRepository
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SingUp @Inject constructor(
    private val authRepository: AuthRepository,
    private val repository: FirestoreRepository
) {
    suspend operator fun invoke(email: String, password: String): FirebaseUser? {
        val user = withContext(Dispatchers.IO) {
            authRepository.signUpWithEmailPassword(
                email,
                password
            )
        }
        user?.let { repository.insertUser(it) }
        return user
    }
}
