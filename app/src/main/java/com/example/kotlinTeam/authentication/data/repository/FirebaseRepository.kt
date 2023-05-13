package com.example.kotlinTeam.authentication.data.repository

import com.example.kotlinTeam.authentication.domain.FirebaseInstanceUseCase
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class FirebaseRepository : FirebaseInstanceUseCase {
    override fun signUp(email: String, password: String): Task<AuthResult> {
        return FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(email, password)
    }

    override fun signIn(email: String, password: String): Task<AuthResult> {
        return FirebaseAuth.getInstance()
            .signInWithEmailAndPassword(email, password)
    }
}
