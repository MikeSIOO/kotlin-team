package com.example.kotlinTeam.authentication.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.kotlinTeam.MainActivity
import com.example.kotlinTeam.R
import com.example.kotlinTeam.authentication.presentation.AuthEvents
import com.example.kotlinTeam.authentication.presentation.viewmodel.FirebaseAuthViewModel
import com.example.kotlinTeam.common.sharedPrefs.SharedPrefs
import com.example.kotlinTeam.common.viewBinding.viewBinding
import com.example.kotlinTeam.databinding.FragmentSignInBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SignInFragment : Fragment(R.layout.fragment_sign_in) {

    private val binding by viewBinding(FragmentSignInBinding::bind)
    private val firebaseAuthViewModel by viewModels<FirebaseAuthViewModel>()
    private lateinit var prefs: SharedPrefs

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity as MainActivity).setBottomNavigationVisibility(View.GONE)

        super.onViewCreated(view, savedInstanceState)

        prefs = SharedPrefs(requireContext())

        binding.notReg.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
        }

        binding.signInButton.setOnClickListener {
            val email = binding.emailText.text.toString()
            val pass = binding.passwordText.text.toString()
            firebaseAuthViewModel.onEvent(AuthEvents.SingIn(email, pass))
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

        viewLifecycleOwner.lifecycleScope.launch {
            firebaseAuthViewModel.UIEventsFlow.collect { event ->
                when (event) {
                    is FirebaseAuthViewModel.UIEvents.Loading -> {
                        binding.progressBarSignIn.visibility = View.GONE
                        binding.errorTxt.text = ""
                    }
                    is FirebaseAuthViewModel.UIEvents.Error -> {
                        binding.errorTxt.text = event.error
                        binding.progressBarSignIn.visibility = View.GONE
                    }
                    is FirebaseAuthViewModel.UIEvents.Message -> {
                        Toast.makeText(requireContext(), event.message, Toast.LENGTH_SHORT)
                            .show()
                    }
                    is FirebaseAuthViewModel.UIEvents.ErrorCode -> {
                        if (event.code == 1)
                            binding.emailLayout.error = "Введите почту"
                        binding.progressBarSignIn.visibility = View.GONE

                        if (event.code == 2)
                            binding.passwordLayout.error = "Введите пароль"
                        binding.progressBarSignIn.visibility = View.GONE
                    }
                    is FirebaseAuthViewModel.UIEvents.Registered -> {
                    }
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                firebaseAuthViewModel.currentUser.collectLatest { user ->
                    user?.let {
                        findNavController().navigate(
                            if (prefs.getIsOnboardingRequired()) {
                                R.id.action_signInFragment_to_onBoarding
                            } else {
                                (activity as MainActivity).setBottomNavigationVisibility(View.VISIBLE)
                                R.id.action_signInFragment_to_actionFeed
                            }
                        )
                    }
                }
            }
        }
    }
}
