package com.example.kotkin_team.products.domain.model

// модель данных
data class ProductsProduct(
    val id: Int,
    val name: String,
    val image: Int,
    val parentId: Int,
)