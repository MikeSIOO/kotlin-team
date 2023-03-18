package com.example.kotkin_team.products.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotkin_team.products.common.Statuses
import com.example.kotkin_team.products.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {
    private val _state = MutableStateFlow(State())
    val state: StateFlow<State> = _state

    fun onEvent(event: Events) {
        when (event) {
            is Events.LoadCategory -> {
                getCategory(event.page)
            }
        }
    }

    private fun getCategory(page: Int) {
        useCases.getCategory(page).onEach { result ->
            when (result) {
                is Statuses.Success -> {
                    _state.value = state.value.copy(
                        category = result.data ?: emptyList(),
                        isLoading = false,
                        error = ""
                    )
                }
                is Statuses.Error -> {
                    _state.value = state.value.copy(
                        isLoading = false,
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Statuses.Loading -> {
                    _state.value = state.value.copy(
                        isLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}