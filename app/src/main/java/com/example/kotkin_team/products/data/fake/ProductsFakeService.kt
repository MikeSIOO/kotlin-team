package com.example.kotkin_team.products.data.fake

import com.example.kotkin_team.R
import com.example.kotkin_team.products.data.model.ProductsCategoryDTO
import com.example.kotkin_team.products.data.model.ProductsCategoryResponseDTO
import com.example.kotkin_team.products.data.model.ProductsProductDTO
import com.example.kotkin_team.products.data.model.ProductsProductResponseDTO
import kotlinx.coroutines.delay

// фейковый сервис отправления запросов
class ProductsFakeService {
    suspend fun getCategory(): ProductsCategoryResponseDTO {
        delay(2000L)
        val fakeCategoryDTO = getFakeCategory()
        return ProductsCategoryResponseDTO(
            status = "Success",
            count = fakeCategoryDTO.size,
            category = fakeCategoryDTO
        )
    }

    suspend fun getProduct(parentId: Int): ProductsProductResponseDTO {
        delay(2000L)
        val fakeProductDTO = getFakeProduct(parentId)
        return ProductsProductResponseDTO(
            status = "Success",
            count = fakeProductDTO.size,
            product = fakeProductDTO
        )
    }

    companion object {
        fun getFakeCategory(): List<ProductsCategoryDTO> {
            val fakeData = listOf(
                ProductsCategoryDTO(
                    0,
                    "Молоко, молочные продукты",
                    R.drawable.ic_launcher_background,
                ),
                ProductsCategoryDTO(
                    1,
                    "Мясо, птица",
                    R.drawable.ic_launcher_foreground,
                ),
                ProductsCategoryDTO(
                    2,
                    "Овощи и фрукты",
                    R.drawable.ic_launcher_background,
                ),
                ProductsCategoryDTO(
                    3,
                    "Рыба",
                    R.drawable.ic_launcher_foreground,
                ),
                ProductsCategoryDTO(
                    4,
                    "Выпечка",
                    R.drawable.ic_launcher_background,
                ),
            )

            return fakeData
        }

        fun getFakeProduct(parentId: Int): List<ProductsProductDTO> {
            val fakeData = listOf(
                ProductsProductDTO(
                    0,
                    "Молоко",
                    R.drawable.ic_launcher_background,
                    0,
                ),
                ProductsProductDTO(
                    1,
                    "Яйца",
                    R.drawable.ic_launcher_background,
                    0,
                ),
                ProductsProductDTO(
                    0,
                    "Мясо",
                    R.drawable.ic_launcher_foreground,
                    1,
                ),
                ProductsProductDTO(
                    1,
                    "Птица",
                    R.drawable.ic_launcher_foreground,
                    1,
                ),
            )

            return when (parentId) {
                0 -> fakeData.subList(0, 2)
                1 -> fakeData.subList(2, 4)
                else -> emptyList()
            }

        }
    }
}