package com.example.kotlinTeam.authentication.presentation

sealed class AuthEvents {
    data class SingUp(val email: String, val password: String, val confirmedPassword: String) : AuthEvents()
    data class SingIn(val email: String, val password: String) : AuthEvents()
    data class ResetPassword(val email: String) : AuthEvents()
}
