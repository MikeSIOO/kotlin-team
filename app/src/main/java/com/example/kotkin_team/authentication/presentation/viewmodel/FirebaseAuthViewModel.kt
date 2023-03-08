package com.example.kotkin_team.authentication.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class FirebaseAuthViewModel: ViewModel() {

    fun getFirebaseAuthInstance(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }
}