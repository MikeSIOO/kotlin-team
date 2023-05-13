package com.example.kotlinTeam.common.data.repository.firebase

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class FirebaseAuthenticator : BaseAuthenticator {
    override suspend fun signUpWithEmailPassword(email: String, password: String): FirebaseUser? {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password).await()
        return FirebaseAuth.getInstance().currentUser
    }

    override suspend fun signInWithEmailPassword(email: String, password: String): FirebaseUser? {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email , password).await()
        return FirebaseAuth.getInstance().currentUser
    }

    override suspend fun signInWithCustomToken(token: String): FirebaseUser? {
        FirebaseAuth.getInstance().signInWithCustomToken(token).await()
        return FirebaseAuth.getInstance().currentUser
    }

    override fun signOut(): FirebaseUser? {
        FirebaseAuth.getInstance().signOut()
        return FirebaseAuth.getInstance().currentUser
    }

    override fun getUser(): FirebaseUser? {
        return FirebaseAuth.getInstance().currentUser
    }

    override suspend fun sendPasswordReset(email: String) {
        FirebaseAuth.getInstance().sendPasswordResetEmail(email).await()
    }
}