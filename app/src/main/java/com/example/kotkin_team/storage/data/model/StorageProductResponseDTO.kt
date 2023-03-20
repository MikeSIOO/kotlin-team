package com.example.kotkin_team.storage.data.model

// Модель ответа от сервиса
data class StorageProductResponseDTO(
    val status: String,
    val count: Int,
    val product: List<StorageProductDTO>
)