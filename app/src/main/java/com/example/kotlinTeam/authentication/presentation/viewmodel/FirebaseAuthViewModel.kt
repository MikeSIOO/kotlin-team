package com.example.kotlinTeam.authentication.presentation.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlinTeam.authentication.data.repository.FirebaseRepository
import com.example.kotlinTeam.authentication.presentation.SignEvents
import com.example.kotlinTeam.authentication.presentation.SignInState
import com.example.kotlinTeam.authentication.presentation.SignUpState
import com.example.kotlinTeam.profile.presentation.ProfileFragmentEvents
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class FirebaseAuthViewModel : ViewModel() {

    private val firebaseRepository: FirebaseRepository = FirebaseRepository()

    private val _stateSignUp = MutableStateFlow(SignUpState())
    val stateSignUp: StateFlow<SignUpState> = _stateSignUp

    private val _stateSignIn = MutableStateFlow(SignInState())
    val stateSignIn: StateFlow<SignInState> = _stateSignIn

    suspend fun onEvent(event: SignEvents) {
        when (event) {
            is SignEvents.SignUp -> {
                signUp(event.context, event.email, event.pasword, event.confirmedPassword)
            }
            is SignEvents.SignIn -> {
                signIn(event.context, event.email, event.password)
            }
        }
    }

    suspend fun signUp(
        context: Context,
        email: String,
        pasword: String,
        confirmedPassword: String
    ) {
        if (withContext(Dispatchers.IO) {
                async {
                    checkSignUp(
                        context,
                        email,
                        pasword,
                        confirmedPassword
                    )
                }
            }.await() == true) {

            _stateSignUp.value = stateSignUp.value.copy(
                isSignUpSuccesfull = true
            )

        }
    }

    private suspend fun checkSignUp(
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

    private suspend fun signUpUser(
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

    suspend fun signIn(context: Context, email: String, password: String) {
        if (withContext(Dispatchers.IO) {
                async {
                    checkSignIn(context, email, password)
                }
            }.await() == true) {

            _stateSignIn.value = stateSignIn.value.copy(
                isSignInSuccesfull = true
            )
        }
    }

    private suspend fun checkSignIn(context: Context, email: String, password: String): Boolean? {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            var isSignSuccess: Boolean? = null
            signInUser(context, email, password) {
                isSignSuccess = it
            }
            return isSignSuccess
        } else {
            Toast.makeText(context, WRONG_DATA, Toast.LENGTH_SHORT).show()
        }
        return false
    }

    private suspend fun signInUser(
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

    private fun getToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val WRONG_DATA = "Вы не ввели все данные"
        private const val WRONG_PASSWORD_REPEAT = "Пароли не совпадают"
    }
}
