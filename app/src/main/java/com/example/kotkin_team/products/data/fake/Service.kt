package com.example.kotkin_team.products.data.fake

import com.example.kotkin_team.R
import com.example.kotkin_team.products.data.model.CategoryDTO
import com.example.kotkin_team.products.data.model.ResponseDTO
import kotlinx.coroutines.delay

// фейковый сервис отправления запросов
class Service {
    suspend fun getFakeProducts(page: Int): ResponseDTO {
        delay(1000L)
        val fakeCategoryDTO = getFakeData(page)
        return ResponseDTO(
            status = "Success",
            count = fakeCategoryDTO.size,
            category = fakeCategoryDTO
        )
    }

    companion object {
//        private const val LIMIT_ON_PAGE = 3

        fun getFakeData(page: Int): List<CategoryDTO> {
            val fakeData = listOf(
                CategoryDTO(
                    0,
                    "Молоко, молочные продукты",
                    R.drawable.ic_launcher_background,
                ),
                CategoryDTO(
                    0,
                    "Мясо, птица",
                    R.drawable.ic_launcher_foreground,
                ),
                CategoryDTO(
                    0,
                    "Овощи и фрукты",
                    R.drawable.ic_launcher_background,
                ),
                CategoryDTO(
                    0,
                    "Мясо, птица",
                    R.drawable.ic_launcher_foreground,
                ),
                CategoryDTO(
                    0,
                    "Овощи и фрукты",
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