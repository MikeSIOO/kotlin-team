package com.example.kotlinTeam.common.data.repository.firebase

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseUser

interface BaseAuthenticator {

    suspend fun signUpWithEmailPassword(email:String , password:String) : FirebaseUser?

    suspend fun signInWithEmailPassword(email: String , password: String) : FirebaseUser?

    suspend fun signInWithCustomToken(token: String) : FirebaseUser?

    fun signOut() : FirebaseUser?

    fun getUser() : FirebaseUser?

    suspend fun sendPasswordReset(email :String)
}