package com.example.kotkin_team.storage.data.api.service

import com.example.kotkin_team.R
import com.example.kotkin_team.storage.data.api.model.StorageCategoryDto
import com.example.kotkin_team.storage.data.api.model.StorageCategoryResponseDto
import com.example.kotkin_team.storage.data.api.model.StorageProductDto
import com.example.kotkin_team.storage.data.api.model.StorageProductResponseDto
import kotlinx.coroutines.delay

// фейковый сервис отправления запросов
class StorageFakeService {
    suspend fun getCategory(): StorageCategoryResponseDto {
        delay(2000L)
        val fakeCategoryDTO = getFakeCategory()
        return StorageCategoryResponseDto(
            status = "Success",
            count = fakeCategoryDTO.size,
            category = fakeCategoryDTO
        )
    }

    suspend fun getProduct(parentId: Int): StorageProductResponseDto {
        delay(2000L)
        val fakeProductDTO = getFakeProduct(parentId)
        return StorageProductResponseDto(
            status = "Success",
            count = fakeProductDTO.size,
            product = fakeProductDTO
        )
    }

    companion object {
        fun getFakeCategory(): List<StorageCategoryDto> {
            return listOf(
                StorageCategoryDto(
                    0,
                    "Молоко, молочные продукты",
                    R.drawable.ic_launcher_background,
                ),
                StorageCategoryDto(
                    1,
                    "Мясо, птица",
                    R.drawable.ic_launcher_foreground,
                ),
                StorageCategoryDto(
                    2,
                    "Овощи и фрукты",
                    R.drawable.ic_launcher_background,
                ),
                StorageCategoryDto(
                    3,
                    "Рыба",
                    R.drawable.ic_launcher_foreground,
                ),
                StorageCategoryDto(
                    4,
                    "Выпечка",
                    R.drawable.ic_launcher_background,
                ),
                StorageCategoryDto(
                    5,
                    "Молоко, молочные продукты2",
                    R.drawable.ic_launcher_background,
                ),
                StorageCategoryDto(
                    6,
                    "Мясо, птица2",
                    R.drawable.ic_launcher_foreground,
                ),
                StorageCategoryDto(
                    7,
                    "Овощи и фрукты2",
                    R.drawable.ic_launcher_background,
                ),
                StorageCategoryDto(
                    8,
                    "Рыба2",
                    R.drawable.ic_launcher_foreground,
                ),
                StorageCategoryDto(
                    9,
                    "Выпечка2",
                    R.drawable.ic_launcher_background,
                ),
                StorageCategoryDto(
                    10,
                    "Молоко, молочные продукты3",
                    R.drawable.ic_launcher_background,
                ),
                StorageCategoryDto(
                    11,
                    "Мясо, птица3",
                    R.drawable.ic_launcher_foreground,
                ),
                StorageCategoryDto(
                    12,
                    "Овощи и фрукты3",
                    R.drawable.ic_launcher_background,
                ),
                StorageCategoryDto(
                    13,
                    "Рыба3",
                    R.drawable.ic_launcher_foreground,
                ),
                StorageCategoryDto(
                    14,
                    "Выпечка3",
                    R.drawable.ic_launcher_background,
                ),
            )
        }

        fun getFakeProduct(parentId: Int): List<StorageProductDto> {
            val fakeData = listOf(
                StorageProductDto(
                    0,
                    "Молоко",
                    R.drawable.ic_launcher_background,
                    0,
                ),
                StorageProductDto(
                    1,
                    "Яйца",
                    R.drawable.ic_launcher_background,
                    0,
                ),
                StorageProductDto(
                    2,
                    "Мясо",
                    R.drawable.ic_launcher_foreground,
                    1,
                ),
                StorageProductDto(
                    3,
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