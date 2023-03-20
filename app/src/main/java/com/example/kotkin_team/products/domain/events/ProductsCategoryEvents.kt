package com.example.kotkin_team.products.domain.events

sealed class ProductsCategoryEvents {
    class LoadCategory : ProductsCategoryEvents()
}