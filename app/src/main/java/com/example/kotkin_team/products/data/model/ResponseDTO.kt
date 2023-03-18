package com.example.kotkin_team.products.data.model

// Модель ответа от сервиса
data class ResponseDTO(
    val status: String,
    val count: Int,
    val category: List<CategoryDTO>
)