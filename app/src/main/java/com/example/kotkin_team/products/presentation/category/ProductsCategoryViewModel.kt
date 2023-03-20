package com.example.kotkin_team.products.presentation.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotkin_team.products.common.ProductsStatuses
import com.example.kotkin_team.products.domain.events.ProductsCategoryEvents
import com.example.kotkin_team.products.domain.state.ProductsCategoryState
import com.example.kotkin_team.products.domain.use_cases.ProductsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ProductsCategoryViewModel @Inject constructor(
    private val productsUseCases: ProductsUseCases
) : ViewModel() {
    private val _productsCategoryState = MutableStateFlow(ProductsCategoryState())
    val productsCategoryState: StateFlow<ProductsCategoryState> = _productsCategoryState

    fun onEvent(event: ProductsCategoryEvents) {
        when (event) {
            is ProductsCategoryEvents.LoadCategory -> {
                getCategory()
            }
        }
    }

    private fun getCategory() {
        productsUseCases.productsGetCategory().onEach { result ->
            when (result) {
                is ProductsStatuses.Success -> {
                    _productsCategoryState.value = productsCategoryState.value.copy(
                        productsCategory = result.data ?: emptyList(),
                        isLoading = false,
                        error = ""
                    )
                }
                is ProductsStatuses.Error -> {
                    _productsCategoryState.value = productsCategoryState.value.copy(
                        isLoading = false,
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is ProductsStatuses.Loading -> {
                    _productsCategoryState.value = productsCategoryState.value.copy(
                        isLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}