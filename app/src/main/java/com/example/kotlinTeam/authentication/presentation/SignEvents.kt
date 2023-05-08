package com.example.kotlinTeam.authentication.presentation

import android.content.Context

sealed class SignEvents {
    data class SignUp(
        val context: Context,
        val email: String,
        val pasword: String,
        val confirmedPassword: String
    ) : SignEvents()

    data class SignIn(
        val context: Context,
        val email: String,
        val password: String
    ) : SignEvents()
}
