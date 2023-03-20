package com.example.kotkin_team.products.domain.use_cases

import com.example.kotkin_team.products.common.ProductsStatuses
import com.example.kotkin_team.products.domain.model.ProductsProduct
import com.example.kotkin_team.products.domain.repository.ProductsRepository
import kotlinx.coroutines.flow.Flow

class ProductsGetProduct(
    private val productsRepository: ProductsRepository
) {
    operator fun invoke(parentId: Int): Flow<ProductsStatuses<List<ProductsProduct>>> {
        return productsRepository.getProduct(parentId)
    }
}