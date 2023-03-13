package com.example.kotkin_team.authentication.domain

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

interface FirebaseInstanceUseCase {

    fun signUp(email: String, password: String): Task<AuthResult>

    fun signIn(email: String, password: String): Task<AuthResult>
}