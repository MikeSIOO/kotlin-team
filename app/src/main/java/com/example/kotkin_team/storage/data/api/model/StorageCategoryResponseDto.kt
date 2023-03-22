package com.example.kotkin_team.storage.data.api.model

// Модель ответа от сервиса
data class StorageCategoryResponseDto(
    val status: String,
    val count: Int,
    val category: List<StorageCategoryDto>
)