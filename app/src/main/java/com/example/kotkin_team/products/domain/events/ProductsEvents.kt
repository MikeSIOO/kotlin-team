package com.example.kotkin_team.products.domain.events

sealed class ProductsEvents {
    data class LoadCategory(val page: Int) : ProductsEvents()
}