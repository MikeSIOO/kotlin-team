package com.example.kotkin_team.products.data.model

import com.example.kotkin_team.products.domain.model.ProductsCategory

// Модель данных в ответе от сервиса
data class ProductsCategoryDTO(
    val id: Int,
    val name: String,
    val image: Int,
) {
    fun toCategory(): ProductsCategory {
        return ProductsCategory(
            id = id,
            name = name,
            image = image
        )
    }
}