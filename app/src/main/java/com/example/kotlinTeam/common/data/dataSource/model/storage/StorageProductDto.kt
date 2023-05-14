package com.example.kotlinTeam.common.data.dataSource.model.storage

// Модель данных в ответе от сервиса
data class StorageProductDto(
    val id: String? = null,
    val title: String? = null,
    val image: String? = null,
    val parentId: String? = null,
)
