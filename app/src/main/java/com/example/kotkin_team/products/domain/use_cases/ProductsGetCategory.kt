package com.example.kotkin_team.products.domain.use_cases

import com.example.kotkin_team.products.common.ProductsStatuses
import com.example.kotkin_team.products.domain.model.ProductsCategory
import com.example.kotkin_team.products.domain.repository.ProductsRepository
import kotlinx.coroutines.flow.Flow

class ProductsGetCategory(
    private val productsRepository: ProductsRepository
) {
    operator fun invoke(): Flow<ProductsStatuses<List<ProductsCategory>>> {
        return productsRepository.getCategory()
    }
}