package com.example.kotlinTeam.authentication.domain

import com.example.kotlinTeam.common.data.repository.AuthRepository
import com.example.kotlinTeam.profile.domain.repository.ProfileRepository
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SingUp @Inject constructor(
    private val repository: AuthRepository
)  {
    suspend operator fun invoke(email: String, password: String): FirebaseUser? {
        return withContext(Dispatchers.IO) { repository.signUpWithEmailPassword(email, password) }
    }
}