package com.example.kotkin_team.authentication.presentation.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.kotkin_team.MainActivity
import com.example.kotkin_team.R
import com.example.kotkin_team.authentication.presentation.viewmodel.FirebaseAuthViewModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText


class SignInFragment : Fragment(R.layout.fragment_sign_in) {

    private val firebaseAuthViewModel by viewModels<FirebaseAuthViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val notReg = view.findViewById<TextView>(R.id.not_reg)

        notReg.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
        }

        val signInButton = view.findViewById<MaterialButton>(R.id.sign_in_button)

        signInButton.setOnClickListener {
            val email = view.findViewById<TextInputEditText>(R.id.email_text).text.toString()
            val pass = view.findViewById<TextInputEditText>(R.id.password_text).text.toString()

            //Если поля не пустые
            if (email.isNotEmpty() && pass.isNotEmpty()) {
                firebaseAuthViewModel.getFirebaseAuthInstance()
                    //вход в приложение
                    .signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                        if (it.isSuccessful) {
                            Toast.makeText(
                                signInButton.context,
                                "${email} успешно вошел",
                                Toast.LENGTH_SHORT
                            ).show()
                            TODO("Добавить навигацию на главный фрагмент")
                        } else {
                            Toast.makeText(
                                signInButton.context,
                                it.exception.toString(),
                                Toast.LENGTH_SHORT
                            ).show()
                            Log.d("Firebase", it.exception.toString())
                        }
                    }
            } else {
                Toast.makeText(signInButton.context, WRONG_DATA, Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        private const val WRONG_DATA = "Вы не ввели все данные"
    }
}