package com.example.kotlinTeam.onBoarding.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinTeam.onBoarding.common.OnBoardingStatuses
import com.example.kotlinTeam.onBoarding.domain.events.OnBoardingEvents
import com.example.kotlinTeam.onBoarding.domain.model.OnBoardingPage
import com.example.kotlinTeam.onBoarding.domain.state.OnBoardingState
import com.example.kotlinTeam.onBoarding.domain.useCases.GetOnBoardingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val getOnBoardingUseCase: GetOnBoardingUseCase
) : ViewModel() {
    private val _onBoardingState = MutableStateFlow(OnBoardingState())
    val onBoardingState: StateFlow<OnBoardingState> = _onBoardingState

    private var pageNumber = 0
    private val _pageState = MutableLiveData<OnBoardingPage?>()
    val pageState: LiveData<OnBoardingPage?> = _pageState

    init {
        getOnBoarding()
    }

    fun onEvent(event: OnBoardingEvents) {
        when (event) {
            is OnBoardingEvents.NextPage -> {
                nextPage()
            }
        }
    }

    private fun getOnBoarding() {
        getOnBoardingUseCase().onEach { result ->
            when (result) {
                is OnBoardingStatuses.Success -> {
                    _onBoardingState.value = onBoardingState.value.copy(
                        onBoardingPage = result.data ?: emptyList(),
                        isLoading = false,
                        error = ""
                    )
                    nextPage()
                }
                is OnBoardingStatuses.Error -> {
                    _onBoardingState.value = onBoardingState.value.copy(
                        isLoading = false,
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is OnBoardingStatuses.Loading -> {
                    _onBoardingState.value = onBoardingState.value.copy(
                        isLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun nextPage() {
        if (pageNumber >= _onBoardingState.value.onBoardingPage.size) {
            _pageState.value = null
        } else {
            _pageState.value = _onBoardingState.value.onBoardingPage[pageNumber++]
        }
    }
}
