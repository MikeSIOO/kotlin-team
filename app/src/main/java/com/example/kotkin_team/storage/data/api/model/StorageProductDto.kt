package com.example.kotkin_team.storage.data.api.model

// Модель данных в ответе от сервиса
data class StorageProductDto(
    val id: Int,
    val name: String,
    val image: String,
    val parentId: Int,
)
