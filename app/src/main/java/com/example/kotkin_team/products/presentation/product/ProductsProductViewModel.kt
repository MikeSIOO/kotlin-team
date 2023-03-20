package com.example.kotkin_team.products.presentation.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotkin_team.products.common.ProductsStatuses
import com.example.kotkin_team.products.domain.events.ProductsProductEvents
import com.example.kotkin_team.products.domain.state.ProductsProductState
import com.example.kotkin_team.products.domain.use_cases.ProductsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ProductsProductViewModel @Inject constructor(
    private val productsUseCases: ProductsUseCases
) : ViewModel() {
    private val _productsProductState = MutableStateFlow(ProductsProductState())
    val productsProductState: StateFlow<ProductsProductState> = _productsProductState

    fun onEvent(event: ProductsProductEvents) {
        when (event) {
            is ProductsProductEvents.LoadProduct -> {
                getProduct(event.parentId)
            }
        }
    }

    private fun getProduct(parentId: Int) {
        productsUseCases.productsGetProduct(parentId).onEach { result ->
            when (result) {
                is ProductsStatuses.Success -> {
                    _productsProductState.value = productsProductState.value.copy(
                        productsProduct = result.data ?: emptyList(),
                        isLoading = false,
                        error = ""
                    )
                }
                is ProductsStatuses.Error -> {
                    _productsProductState.value = productsProductState.value.copy(
                        isLoading = false,
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is ProductsStatuses.Loading -> {
                    _productsProductState.value = productsProductState.value.copy(
                        isLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}