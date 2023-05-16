package com.example.kotlinTeam.authentication.domain

import com.example.kotlinTeam.common.data.repository.AuthRepository
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SingIn @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String): FirebaseUser? {
        return withContext(Dispatchers.IO) { repository.signInWithEmailPassword(email, password) }
    }
}
