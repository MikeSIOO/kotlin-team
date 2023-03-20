package com.example.kotkin_team.products.data.model

import com.example.kotkin_team.products.domain.model.ProductsProduct

// Модель данных в ответе от сервиса
data class ProductsProductDTO(
    val id: Int,
    val name: String,
    val image: Int,
    val parentId: Int,
) {
    fun toProduct(): ProductsProduct {
        return ProductsProduct(
            id = id,
            name = name,
            image = image,
            parentId = parentId
        )
    }
}