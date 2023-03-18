package com.example.kotkin_team.products.data.model

import com.example.kotkin_team.products.domain.model.Category

// Модель данных в ответе от сервиса
data class CategoryDTO(
    val id: Int,
    val name: String,
    val image: Int,
) {
    fun toCategory(): Category {
        return Category(
            id = id,
            name = name,
            image = image
        )
    }
}