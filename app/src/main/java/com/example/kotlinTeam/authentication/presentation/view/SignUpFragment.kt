package com.example.kotlinTeam.authentication.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.kotlinTeam.MainActivity
import com.example.kotlinTeam.R
import com.example.kotlinTeam.authentication.presentation.AuthEvents
import com.example.kotlinTeam.authentication.presentation.viewmodel.FirebaseAuthViewModel
import com.example.kotlinTeam.common.viewBinding.viewBinding
import com.example.kotlinTeam.databinding.FragmentSignUpBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
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
        (activity as MainActivity).setBottomNavigationVisibility(View.GONE)

        super.onViewCreated(view, savedInstanceState)

        binding.alreadyReg.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
        }

        binding.signUpButton.setOnClickListener {
            val email = binding.emailText.text.toString()
            val pass = binding.passwordText.text.toString()
            val confirmPass = binding.confirmPasswordText.text.toString()
            firebaseAuthViewModel.onEvent(AuthEvents.SingUp(email, pass, confirmPass))
        }

        binding.emailText.setOnFocusChangeListener { _, b ->
            if (b) {
                binding.emailLayout.isErrorEnabled = false
            }
        }
        binding.passwordText.setOnFocusChangeListener { _, b ->
            if (b) {
                binding.passwordLayout.isErrorEnabled = false
            }
        }
        binding.confirmPasswordText.setOnFocusChangeListener { _, b ->
            if (b) {
                binding.confirmPasswordLayout.isErrorEnabled = false
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            firebaseAuthViewModel.UIEventsFlow.collect { event ->
                when (event) {
                    is FirebaseAuthViewModel.UIEvents.Loading -> {
                        binding.progressBarSignUp.visibility = View.VISIBLE
                        binding.errorTxt.text = ""
                        binding.emailLayout.isErrorEnabled = false
                        binding.passwordLayout.isErrorEnabled = false
                        binding.confirmPasswordLayout.isErrorEnabled = false
                    }
                    is FirebaseAuthViewModel.UIEvents.Error -> {
                        binding.errorTxt.text = event.error
                        binding.progressBarSignUp.visibility = View.GONE
                    }
                    is FirebaseAuthViewModel.UIEvents.Message -> {
                        Toast.makeText(requireContext(), event.message, Toast.LENGTH_SHORT)
                            .show()
                    }
                    is FirebaseAuthViewModel.UIEvents.ErrorCode -> {
                        if (event.code == 1)
                            binding.emailLayout.error = "Введите почту"
                        binding.progressBarSignUp.visibility = View.GONE

                        if (event.code == 2)
                            binding.passwordLayout.error = "Введите пароль"
                        binding.progressBarSignUp.visibility = View.GONE

                        if (event.code == 3)
                            binding.confirmPasswordLayout.error = "Пароли не совпадают"
                        binding.progressBarSignUp.visibility = View.GONE
                    }
                    is FirebaseAuthViewModel.UIEvents.Registered -> {
                        findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
                    }
                }
            }
        }
    }
}
