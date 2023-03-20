package com.example.kotkin_team.storage.data.model

import com.example.kotkin_team.storage.domain.model.StorageProduct

// Модель данных в ответе от сервиса
data class StorageProductDTO(
    val id: Int,
    val name: String,
    val image: Int,
    val parentId: Int,
) {
    fun toProduct(): StorageProduct {
        return StorageProduct(
            id = id,
            name = name,
            image = image,
            parentId = parentId
        )
    }
}