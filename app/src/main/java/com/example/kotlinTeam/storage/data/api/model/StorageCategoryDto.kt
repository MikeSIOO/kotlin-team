package com.example.kotlinTeam.storage.data.api.model

// Модель данных в ответе от сервиса
data class StorageCategoryDto(
    val id: Int,
    val name: String,
    val image: String,
)
