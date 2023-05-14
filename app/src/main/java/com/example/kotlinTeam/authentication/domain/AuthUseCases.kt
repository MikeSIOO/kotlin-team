package com.example.kotlinTeam.authentication.domain

import javax.inject.Inject

data class AuthUseCases @Inject constructor (
    val singIn: SingIn,
    val singUp: SingUp,
    val getCurrentUser: GetCurrentUser,
    val resetPassword: ResetPassword
)