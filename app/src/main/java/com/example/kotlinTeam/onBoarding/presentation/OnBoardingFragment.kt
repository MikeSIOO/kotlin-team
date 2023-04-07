package com.example.kotlinTeam.onBoarding.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.kotlinTeam.R
import com.example.kotlinTeam.common.viewBinding.viewBinding
import com.example.kotlinTeam.databinding.FragmentOnboardingBinding
import com.example.kotlinTeam.onBoarding.domain.events.OnBoardingEvents
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
internal class OnBoardingFragment : Fragment() {
    companion object {
        fun newInstance() = OnBoardingFragment()
    }

    private val binding by viewBinding(FragmentOnboardingBinding::bind)
    private val viewModel: OnBoardingViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_onboarding, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.onBoardingState.collectLatest {
                when {
                    it.isLoading -> {
                        Toast.makeText(context, "LOADING...", Toast.LENGTH_SHORT).show()
                    }
                    it.error.isNotBlank() -> {
                        Toast.makeText(context, it.error, Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        binding.onBoarding.setOnClickListener {
                            viewModel.onEvent(OnBoardingEvents.NextPage)
                        }
                    }
                }
            }
        }

        viewModel.index.observe(viewLifecycleOwner) {
            if (viewModel.index.value!! != -1) {
                binding.text.text =
                    viewModel.onBoardingState.value.onBoardingPage[viewModel.index.value!!].text
            }
        }
    }
}
