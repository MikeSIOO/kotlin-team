package com.example.kotkin_team.products.domain.repository

import com.example.kotkin_team.products.common.ProductsStatuses
import com.example.kotkin_team.products.domain.model.ProductsCategory
import com.example.kotkin_team.products.domain.model.ProductsProduct
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {
    fun getCategory(): Flow<ProductsStatuses<List<ProductsCategory>>>

    fun getProduct(parentId: Int): Flow<ProductsStatuses<List<ProductsProduct>>>
}