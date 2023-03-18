package com.example.kotkin_team.products.data.fake

import com.example.kotkin_team.R
import com.example.kotkin_team.products.data.model.ProductsCategoryDTO
import com.example.kotkin_team.products.data.model.ProductsResponseDTO
import kotlinx.coroutines.delay

// фейковый сервис отправления запросов
class ProductsFakeService {
    suspend fun getFakeProducts(page: Int): ProductsResponseDTO {
        delay(1000L)
        val fakeCategoryDTO = getFakeData(page)
        return ProductsResponseDTO(
            status = "Success",
            count = fakeCategoryDTO.size,
            category = fakeCategoryDTO
        )
    }

    companion object {
//        private const val LIMIT_ON_PAGE = 3

        fun getFakeData(page: Int): List<ProductsCategoryDTO> {
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
//            return fakeData.subList(
//                page * LIMIT_ON_PAGE,
//                (page + 1) * LIMIT_ON_PAGE - 1
//            )
        }
    }
}