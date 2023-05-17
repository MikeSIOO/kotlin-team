package com.example.kotlinTeam.profile.domain.useCases

import com.example.kotlinTeam.common.data.repository.AuthRepository
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LogOut @Inject constructor(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(): FirebaseUser? {
        return withContext(Dispatchers.IO) { repository.signOut() }
    }
}
