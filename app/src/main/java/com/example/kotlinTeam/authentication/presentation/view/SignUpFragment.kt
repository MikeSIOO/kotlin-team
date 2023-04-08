package com.example.kotlinTeam.authentication.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.kotlinTeam.R
import com.example.kotlinTeam.authentication.presentation.viewmodel.FirebaseAuthViewModel
import com.example.kotlinTeam.common.viewBinding.viewBinding
import com.example.kotlinTeam.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment() {

    private val binding by viewBinding(FragmentSignUpBinding::bind)

    private val firebaseAuthViewModel by viewModels<FirebaseAuthViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.alreadyReg.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
        }

        binding.signUpButton.setOnClickListener {
            val email = binding.emailText.text.toString()
            val pass = binding.passwordText.text.toString()
            val confirmPass = binding.confirmPasswordText.text.toString()

            if (firebaseAuthViewModel.checkSignUp(requireContext(), email, pass, confirmPass)) {
                findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
            }
        }
    }
}