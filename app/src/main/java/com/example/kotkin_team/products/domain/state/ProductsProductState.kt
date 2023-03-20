package com.example.kotkin_team.products.domain.state

import com.example.kotkin_team.products.domain.model.ProductsProduct

data class ProductsProductState(
    val isLoading: Boolean = false,
    val productsProduct: List<ProductsProduct> = emptyList(),
    val error: String = ""
)