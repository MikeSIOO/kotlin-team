package com.example.kotkin_team.testOnboarding.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotkin_team.testOnboarding.domain.GetOnboardingUseCase
import com.example.kotkin_team.testOnboarding.domain.OnboardingScreenEvent
import com.example.kotkin_team.testOnboarding.domain.OnboardingScreenState

internal class OnboardingViewModel : ViewModel() {
    private val _state = MutableLiveData<OnboardingScreenState>()
    val state: LiveData<OnboardingScreenState> = _state

    private lateinit var getOnboardingUseCase: GetOnboardingUseCase

    init {
        _state.value = _state.value?.copy(
            flow = getOnboardingUseCase.getOnboardingFlow()
        )
    }

    fun reduceEvent(screenEvent: OnboardingScreenEvent) {
        when (screenEvent) {
            OnboardingScreenEvent.OnNextClick -> {

                val currentValue = _state.value
                _state.value = currentValue?.copy(
                    currentPage = currentValue.currentPage + 1,
                    isLoading = true
                )
            }

            is OnboardingScreenEvent.OnPageSelect -> {
                TODO()
            }
        }
    }
}