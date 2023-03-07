package com.example.kotkin_team.products.domain

import com.example.kotkin_team.products.data.ProductsFlow

internal interface GetProductsUseCase {
    fun getProductsFlow() : ProductsFlow
}