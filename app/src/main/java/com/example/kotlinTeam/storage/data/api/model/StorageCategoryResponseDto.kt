package com.example.kotlinTeam.storage.data.api.model

// Модель ответа от сервиса
data class StorageCategoryResponseDto(
    val status: String,
    val count: Int,
    val category: List<StorageCategoryDto>
)
