package com.example.kotkin_team.authentication.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.kotkin_team.MainActivity
import com.example.kotkin_team.R
import com.google.android.material.textfield.TextInputEditText


class SignUpFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val alreadyReg = view.findViewById<TextView>(R.id.already_reg)
        alreadyReg.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
        }

        val signUpButton = view.findViewById<Button>(R.id.sign_up_button)

        signUpButton.setOnClickListener {
            val email = view.findViewById<TextInputEditText>(R.id.email_text).text.toString()
            val pass = view.findViewById<TextInputEditText>(R.id.password_text).text.toString()
            val confirmPass =
                view.findViewById<TextInputEditText>(R.id.confirm_password_text).text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty()) {
                if (pass == confirmPass) {
                    (activity as MainActivity).getFirebaseAuthInstance()
                        .createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                            if (it.isSuccessful) {
                                Toast.makeText(
                                    signUpButton.context,
                                    "${email} успешно добавлен в аккаунт",
                                    Toast.LENGTH_SHORT
                                ).show()
                                findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
                            } else {
                                Toast.makeText(
                                    signUpButton.context,
                                    it.exception.toString(),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                } else {
                    Toast.makeText(signUpButton.context, WRONG_PASSWORD_REPEAT, Toast.LENGTH_SHORT)
                        .show()
                }
            } else {
                Toast.makeText(signUpButton.context, WRONG_DATA, Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    companion object {
        private const val WRONG_DATA = "Вы не ввели все данные"
        private const val WRONG_PASSWORD_REPEAT = "Пароли не совпадают"
    }
}