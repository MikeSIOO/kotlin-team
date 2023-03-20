package com.example.kotkin_team.products.data.fake

import com.example.kotkin_team.R
import com.example.kotkin_team.products.data.model.ProductsCategoryDTO
import com.example.kotkin_team.products.data.model.ProductsCategoryResponseDTO
import com.example.kotkin_team.products.data.model.ProductsProductDTO
import com.example.kotkin_team.products.data.model.ProductsProductResponseDTO
import kotlinx.coroutines.delay

// фейковый сервис отправления запросов
class ProductsFakeService {
    suspend fun getCategory(page: Int): ProductsCategoryResponseDTO {
        delay(2000L)
        val fakeCategoryDTO = getFakeCategory(page)
        return ProductsCategoryResponseDTO(
            status = "Success",
            count = fakeCategoryDTO.size,
            category = fakeCategoryDTO
        )
    }

    suspend fun getProduct(page: Int): ProductsProductResponseDTO {
        delay(2000L)
        val fakeProductDTO = getFakeProduct(page)
        return ProductsProductResponseDTO(
            status = "Success",
            count = fakeProductDTO.size,
            product = fakeProductDTO
        )
    }

    companion object {
//        private const val LIMIT_ON_PAGE = 3

        fun getFakeCategory(page: Int): List<ProductsCategoryDTO> {
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

        fun getFakeProduct(page: Int): List<ProductsProductDTO> {
            val fakeData = listOf(
                ProductsProductDTO(
                    0,
                    "Молоко, молочные продукты",
                    R.drawable.ic_launcher_background,
                ),
                ProductsProductDTO(
                    1,
                    "Мясо, птица",
                    R.drawable.ic_launcher_foreground,
                ),
                ProductsProductDTO(
                    2,
                    "Овощи и фрукты",
                    R.drawable.ic_launcher_background,
                ),
                ProductsProductDTO(
                    3,
                    "Рыба",
                    R.drawable.ic_launcher_foreground,
                ),
                ProductsProductDTO(
                    4,
                    "Выпечка",
                    R.drawable.ic_launcher_background,
                ),
            )

            return fakeData
        }
    }
}