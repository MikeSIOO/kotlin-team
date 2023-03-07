package com.example.kotkin_team.products.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotkin_team.products.domain.GetProductsUseCase
import com.example.kotkin_team.products.domain.ProductsScreenEvent
import com.example.kotkin_team.products.domain.ProductsScreenState

internal class ProductsViewModel : ViewModel() {
    private val _state = MutableLiveData<ProductsScreenState>()
    val state: LiveData<ProductsScreenState> = _state

    private lateinit var getProductsUseCase: GetProductsUseCase

    init {
        _state.value = _state.value?.copy(
            flow = getProductsUseCase.getProductsFlow()
        )
    }

    fun reduceEvent(screenEvent: ProductsScreenEvent) {
        when (screenEvent) {
            ProductsScreenEvent.OnNextClick -> {

                val currentValue = _state.value
                _state.value = currentValue?.copy(
                    currentPage = currentValue.currentPage + 1,
                    isLoading = true
                )
            }

            is ProductsScreenEvent.OnPageSelect -> {
                TODO()
            }
        }
    }
}