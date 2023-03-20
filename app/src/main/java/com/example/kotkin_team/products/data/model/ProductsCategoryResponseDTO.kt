package com.example.kotkin_team.products.data.model

// Модель ответа от сервиса
data class ProductsCategoryResponseDTO(
    val status: String,
    val count: Int,
    val category: List<ProductsCategoryDTO>
)