package com.example.kotkin_team.products.domain

import com.example.kotkin_team.products.data.ProductsFlow

internal data class ProductsScreenState(
    val isLoading: Boolean,
    val error: Throwable?,
    val currentPage: Int,
    val flow: ProductsFlow?
)
