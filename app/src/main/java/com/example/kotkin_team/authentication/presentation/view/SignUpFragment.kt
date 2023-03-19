package com.example.kotkin_team.authentication.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.kotkin_team.R
import com.example.kotkin_team.authentication.presentation.viewmodel.FirebaseAuthViewModel
import com.google.android.material.textfield.TextInputEditText


class SignUpFragment : Fragment() {

    private val firebaseAuthViewModel by viewModels<FirebaseAuthViewModel>()

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

            if (firebaseAuthViewModel.checkSignUp(signUpButton.context, email, pass, confirmPass)) {
                firebaseAuthViewModel.signUpUser(signUpButton.context, email, pass)
                findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
            }
        }
    }
}
