package com.example.kotkin_team.products.domain.repository

import com.example.kotkin_team.products.common.ProductsStatuses
import com.example.kotkin_team.products.domain.model.ProductsCategory
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {
    fun getCategory(page: Int): Flow<ProductsStatuses<List<ProductsCategory>>>
}