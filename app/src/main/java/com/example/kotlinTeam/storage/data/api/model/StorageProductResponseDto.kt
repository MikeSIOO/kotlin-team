package com.example.kotlinTeam.storage.data.api.model

// Модель ответа от сервиса
data class StorageProductResponseDto(
    val status: String,
    val count: Int,
    val product: List<StorageProductDto>
)
