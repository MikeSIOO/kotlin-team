package com.example.kotlinTeam.authentication.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.kotlinTeam.R
import com.example.kotlinTeam.authentication.presentation.AuthEvents
import com.example.kotlinTeam.authentication.presentation.viewmodel.FirebaseAuthViewModel
import com.example.kotlinTeam.databinding.FragmentForgotPasswordBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ForgotPasswordFragment : Fragment(R.layout.fragment_forgot_password) {

    private var _binding : FragmentForgotPasswordBinding? = null
    private val binding get() = _binding
    private val viewModel by viewModels<FirebaseAuthViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentForgotPasswordBinding.inflate(inflater , container , false)
        setUpWidgets()
        listenToChannels()
        return binding?.root
    }

    private fun listenToChannels() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.UIEventsFlow.collect { event ->
                when(event){
                    is FirebaseAuthViewModel.UIEvents.Message -> {
                        Toast.makeText(requireContext(), event.message, Toast.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.action_forgotPasswordFragment_to_signInFragment)
                    }
                    is FirebaseAuthViewModel.UIEvents.Error -> {
                        binding?.apply {
                            resetPassProgressBar.isInvisible = true
                            errorText.text = event.error
                        }
                    }
                    is FirebaseAuthViewModel.UIEvents.ErrorCode -> {
                        if(event.code == 1)
                            binding?.apply {
                                userEmailEtvl.error = getString(R.string.enter_email_hint)
                                resetPassProgressBar.isInvisible = true
                            }
                    }

                    else -> {}
                }

            }
        }
    }

    private fun setUpWidgets() {
        binding?.apply {
            buttonResendPassword.setOnClickListener {
                resetPassProgressBar.isVisible = true
                val email = userEmailEtv.text.toString()
                viewModel.onEvent(AuthEvents.ResetPassword(email))
            }
        }
    }
}