package com.example.kotlinTeam.authentication.presentation.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.kotlinTeam.authentication.data.repository.FirebaseRepository
import kotlinx.coroutines.tasks.await

class FirebaseAuthViewModel : ViewModel() {

    private val firebaseRepository: FirebaseRepository = FirebaseRepository()

    suspend fun checkSignUp(
        context: Context,
        email: String,
        pasword: String,
        confirmedPassword: String
    ): Boolean? {
        if (email.isNotEmpty() && pasword.isNotEmpty() && confirmedPassword.isNotEmpty()) {
            if (pasword == confirmedPassword) {
                var isSignSuccess: Boolean? = null
                signUpUser(context, email, pasword) {
                    isSignSuccess = it
                }
                return isSignSuccess
            } else {
                getToast(context, WRONG_PASSWORD_REPEAT)
            }
        } else {
            getToast(context, WRONG_DATA)
        }
        return false
    }

    suspend fun signUpUser(
        context: Context,
        email: String,
        pasword: String,
        onSignCompleted: (Boolean) -> Unit
    ) {
        firebaseRepository.signUp(email, pasword).addOnCompleteListener {
            if (it.isSuccessful) {
                onSignCompleted.invoke(true)
                getToast(
                    context,
                    "$email успешно зарегистрирован",
                )
            } else {
                onSignCompleted.invoke(false)
                getToast(
                    context,
                    it.exception.toString()
                )
            }
        }.await()
    }

    suspend fun checkSignIn(context: Context, email: String, pasword: String): Boolean? {
        if (email.isNotEmpty() && pasword.isNotEmpty()) {
            var isSignSuccess: Boolean? = null
            signInUser(context, email, pasword) {
                isSignSuccess = it
            }
            return isSignSuccess
        } else {
            Toast.makeText(context, WRONG_DATA, Toast.LENGTH_SHORT).show()
        }
        return false
    }

    suspend fun signInUser(
        context: Context,
        email: String,
        pasword: String,
        onSignCompleted: (Boolean) -> Unit
    ) {
        firebaseRepository.signIn(email, pasword).addOnCompleteListener {
            if (it.isSuccessful) {
                onSignCompleted.invoke(true)
                getToast(
                    context,
                    "$email успешно вошел",
                )
            } else {
                onSignCompleted.invoke(true)
                getToast(
                    context,
                    it.exception.toString()
                )
            }
        }.await()
    }

    fun getToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val WRONG_DATA = "Вы не ввели все данные"
        private const val WRONG_PASSWORD_REPEAT = "Пароли не совпадают"
    }
}
