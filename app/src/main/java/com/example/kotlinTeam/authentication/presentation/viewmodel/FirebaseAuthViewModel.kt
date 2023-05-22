package com.example.kotlinTeam.authentication.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinTeam.authentication.domain.AuthUseCases
import com.example.kotlinTeam.authentication.presentation.AuthEvents
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow

@HiltViewModel
class FirebaseAuthViewModel @Inject constructor(
    private val authUseCases: AuthUseCases
) : ViewModel() {

    private val _firebaseUser = MutableStateFlow<FirebaseUser?>(null)
    val currentUser get() = _firebaseUser

    private val eventsChannel = Channel<UIEvents>()
    val UIEventsFlow = eventsChannel.receiveAsFlow()

    init {
        _firebaseUser.value = authUseCases.getCurrentUser()
    }

    fun onEvent(event: AuthEvents) {
        when (event) {
            is AuthEvents.SingUp -> {
                signUpUser(event.email, event.password, event.confirmedPassword)
            }
            is AuthEvents.SingIn -> {
                signInUser(event.email, event.password)
            }
            is AuthEvents.ResetPassword -> {
                verifySendPasswordReset(event.email)
            }
        }
    }

    private fun signInUser(email: String, password: String) = viewModelScope.launch {
        eventsChannel.send(UIEvents.Loading)
        when {
            email.isEmpty() -> {
                eventsChannel.send(UIEvents.ErrorCode(1))
            }
            password.isEmpty() -> {
                eventsChannel.send(UIEvents.ErrorCode(2))
            }
            else -> {
                actualSignInUser(email, password)
            }
        }
    }

    private fun signUpUser(email: String, password: String, confirmPass: String) = viewModelScope.launch {
        eventsChannel.send(UIEvents.Loading)
        when {
            email.isEmpty() -> {
                eventsChannel.send(UIEvents.ErrorCode(1))
            }
            password.isEmpty() -> {
                eventsChannel.send(UIEvents.ErrorCode(2))
            }
            password != confirmPass -> {
                eventsChannel.send(UIEvents.ErrorCode(3))
            }
            else -> {
                actualSignUpUser(email, password)
            }
        }
    }

    private fun actualSignInUser(email: String, password: String) = viewModelScope.launch {
        eventsChannel.send(UIEvents.Loading)
        try {
            val user = authUseCases.singIn(email, password)
            user?.let {
                _firebaseUser.value = it
                eventsChannel.send(UIEvents.Message("Успешно"))
            }
        } catch (e: Exception) {
            val error = e.toString().split(":").toTypedArray()
            eventsChannel.send(UIEvents.Error(error[1]))
        }
    }

    private fun actualSignUpUser(email: String, password: String) = viewModelScope.launch {
        eventsChannel.send(UIEvents.Loading)
        try {
            val user = authUseCases.singUp(email, password)
            user?.let {
                eventsChannel.send(UIEvents.Message("Успешно"))
                eventsChannel.send(UIEvents.Registered)
            }
        } catch (e: Exception) {
            val error = e.toString().split(":").toTypedArray()
            eventsChannel.send(UIEvents.Error(error[1]))
        }
    }

    private fun verifySendPasswordReset(email: String) {
        if (email.isEmpty()) {
            viewModelScope.launch {
                eventsChannel.send(UIEvents.ErrorCode(1))
            }
        } else {
            sendPasswordResetEmail(email)
        }
    }

    private fun sendPasswordResetEmail(email: String) = viewModelScope.launch {
        eventsChannel.send(UIEvents.Loading)
        try {
            val result = authUseCases.resetPassword(email)
            if (result) {
                eventsChannel.send(UIEvents.Message("Письмо отправлено"))
            } else {
                eventsChannel.send(UIEvents.Error("Не удалось сбросить пароль"))
            }
        } catch (e: Exception) {
            val error = e.toString().split(":").toTypedArray()
            eventsChannel.send(UIEvents.Error(error[1]))
        }
    }

    sealed class UIEvents {
        object Registered : UIEvents()
        object Loading : UIEvents()
        data class Message(val message: String) : UIEvents()
        data class ErrorCode(val code: Int) : UIEvents()
        data class Error(val error: String) : UIEvents()
    }
}
