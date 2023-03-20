package com.example.kotkin_team.products.domain.events

sealed class ProductsProductEvents {
    data class LoadProduct(val parentId: Int) : ProductsProductEvents()
}