package com.example.kotkin_team.authentication.presentation.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.kotkin_team.authentication.data.repository.FirebaseRepository

class FirebaseAuthViewModel : ViewModel() {

    val firebaseRepository: FirebaseRepository = FirebaseRepository()

    fun checkSignUp(
        context: Context,
        email: String,
        pasword: String,
        confirmedPassword: String
    ): Boolean {
        if (email.isNotEmpty() && pasword.isNotEmpty() && confirmedPassword.isNotEmpty()) {
            if (pasword == confirmedPassword) {
                return true
            } else {
                getToast(context, WRONG_PASSWORD_REPEAT)
            }
        } else {
            getToast(context, WRONG_DATA)
        }
        return false
    }

    fun signUpUser(context: Context, email: String, pasword: String) {
        firebaseRepository.signUp(email, pasword).addOnCompleteListener {
            if (it.isSuccessful) {
                getToast(
                    context,
                    "$email успешно зарегистрирован",
                )
            } else {
                getToast(
                    context,
                    it.exception.toString()
                )
            }
        }
    }

    fun checkSignIn(context: Context, email: String, pasword: String): Boolean {
        if (email.isNotEmpty() && pasword.isNotEmpty()) {
            return true
        } else {
            Toast.makeText(context, WRONG_DATA, Toast.LENGTH_SHORT).show()
        }
        return false
    }

    fun signInUser(context: Context, email: String, pasword: String) {
        firebaseRepository.signIn(email, pasword).addOnCompleteListener {
            if (it.isSuccessful) {
                getToast(
                    context,
                    "$email успешно вошел",
                )
            } else {
                getToast(
                    context,
                    it.exception.toString()
                )
            }
        }
    }

    fun getToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val WRONG_DATA = "Вы не ввели все данные"
        private const val WRONG_PASSWORD_REPEAT = "Пароли не совпадают"
    }
}
