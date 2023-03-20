package com.example.kotkin_team.products.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotkin_team.products.common.ProductsStatuses
import com.example.kotkin_team.products.domain.events.ProductsEvents
import com.example.kotkin_team.products.domain.state.ProductsCategoryState
import com.example.kotkin_team.products.domain.use_cases.ProductsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val productsUseCases: ProductsUseCases
) : ViewModel() {
    private val _productsState = MutableStateFlow(ProductsCategoryState())
    val productsCategoryState: StateFlow<ProductsCategoryState> = _productsState

    fun onEvent(event: ProductsEvents) {
        when (event) {
            is ProductsEvents.LoadCategory -> {
                getCategory(event.page)
            }
            is ProductsEvents.LoadProduct -> TODO()
        }
    }

    private fun getCategory(page: Int) {
        productsUseCases.productsGetCategory(page).onEach { result ->
            when (result) {
                is ProductsStatuses.Success -> {
                    _productsState.value = productsCategoryState.value.copy(
                        productsCategory = result.data ?: emptyList(),
                        isLoading = false,
                        error = ""
                    )
                }
                is ProductsStatuses.Error -> {
                    _productsState.value = productsCategoryState.value.copy(
                        isLoading = false,
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is ProductsStatuses.Loading -> {
                    _productsState.value = productsCategoryState.value.copy(
                        isLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}