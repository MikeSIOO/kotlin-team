package com.example.kotkin_team.products.data.model

// Модель ответа от сервиса
data class ProductsProductResponseDTO(
    val status: String,
    val count: Int,
    val product: List<ProductsProductDTO>
)