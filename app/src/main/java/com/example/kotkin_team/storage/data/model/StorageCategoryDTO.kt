package com.example.kotkin_team.storage.data.model

import com.example.kotkin_team.storage.domain.model.StorageCategory

// Модель данных в ответе от сервиса
data class StorageCategoryDTO(
    val id: Int,
    val name: String,
    val image: Int,
) {
    fun toCategory(): StorageCategory {
        return StorageCategory(
            id = id,
            name = name,
            image = image
        )
    }
}