package com.example.kotlinTeam.authentication.domain

import com.example.kotlinTeam.common.data.repository.AuthRepository
import javax.inject.Inject

class ResetPassword @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(email: String): Boolean {
        return repository.sendResetPassword(email)
    }
}
