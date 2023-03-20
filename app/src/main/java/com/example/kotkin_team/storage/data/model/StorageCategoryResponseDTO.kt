package com.example.kotkin_team.storage.data.model

// Модель ответа от сервиса
data class StorageCategoryResponseDTO(
    val status: String,
    val count: Int,
    val category: List<StorageCategoryDTO>
)