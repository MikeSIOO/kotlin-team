package com.example.kotlinTeam.authentication.domain

import com.example.kotlinTeam.common.data.repository.AuthRepository
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class GetCurrentUser @Inject constructor(
    private val repository: AuthRepository
)  {
    operator fun invoke(): FirebaseUser? {
        return repository.getCurrentUser()
    }
}