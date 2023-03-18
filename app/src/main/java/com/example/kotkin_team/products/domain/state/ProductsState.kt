package com.example.kotkin_team.products.domain.state

import com.example.kotkin_team.products.domain.model.ProductsCategory

data class ProductsState(
    val isLoading: Boolean = false,
    val productsCategory: List<ProductsCategory> = emptyList(),
    val error: String = ""
)