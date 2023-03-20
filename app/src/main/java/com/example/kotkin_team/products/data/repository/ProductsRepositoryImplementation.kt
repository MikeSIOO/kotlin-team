package com.example.kotkin_team.products.data.repository

import com.example.kotkin_team.products.common.ProductsStatuses
import com.example.kotkin_team.products.data.fake.ProductsFakeService
import com.example.kotkin_team.products.domain.model.ProductsCategory
import com.example.kotkin_team.products.domain.model.ProductsProduct
import com.example.kotkin_team.products.domain.repository.ProductsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

// репозиторий (запрос к сервису и преобразование ответа в модель приложения)
@Singleton
class ProductsRepositoryImplementation @Inject constructor(
    private val productsFakeService: ProductsFakeService
) : ProductsRepository {
    override fun getCategory(page: Int): Flow<ProductsStatuses<List<ProductsCategory>>> = flow {
        try {
            emit(ProductsStatuses.Loading<List<ProductsCategory>>())
            val recipes = productsFakeService.getCategory(page).category.map { it.toCategory() }
            emit(ProductsStatuses.Success<List<ProductsCategory>>(recipes))
        } catch (e: IOException) {
            emit(ProductsStatuses.Error<List<ProductsCategory>>("Не обнаружено соединение с сервером. Проверьте интернет подключение"))
        }
    }

    override fun getProduct(page: Int): Flow<ProductsStatuses<List<ProductsProduct>>> = flow {
        try {
            emit(ProductsStatuses.Loading<List<ProductsProduct>>())
            val recipes = productsFakeService.getProduct(page).product.map { it.toProduct() }
            emit(ProductsStatuses.Success<List<ProductsProduct>>(recipes))
        } catch (e: IOException) {
            emit(ProductsStatuses.Error<List<ProductsProduct>>("Не обнаружено соединение с сервером. Проверьте интернет подключение"))
        }
    }
}