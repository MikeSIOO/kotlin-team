package com.example.kotkin_team.storage.data.fake

import com.example.kotkin_team.R
import com.example.kotkin_team.storage.data.model.StorageCategoryDTO
import com.example.kotkin_team.storage.data.model.StorageCategoryResponseDTO
import com.example.kotkin_team.storage.data.model.StorageProductDTO
import com.example.kotkin_team.storage.data.model.StorageProductResponseDTO
import kotlinx.coroutines.delay

// фейковый сервис отправления запросов
class StorageFakeService {
    suspend fun getCategory(): StorageCategoryResponseDTO {
        delay(2000L)
        val fakeCategoryDTO = getFakeCategory()
        return StorageCategoryResponseDTO(
            status = "Success",
            count = fakeCategoryDTO.size,
            category = fakeCategoryDTO
        )
    }

    suspend fun getProduct(parentId: Int): StorageProductResponseDTO {
        delay(2000L)
        val fakeProductDTO = getFakeProduct(parentId)
        return StorageProductResponseDTO(
            status = "Success",
            count = fakeProductDTO.size,
            product = fakeProductDTO
        )
    }

    companion object {
        fun getFakeCategory(): List<StorageCategoryDTO> {

            return listOf(
                StorageCategoryDTO(
                    0,
                    "Молоко, молочные продукты",
                    R.drawable.ic_launcher_background,
                ),
                StorageCategoryDTO(
                    1,
                    "Мясо, птица",
                    R.drawable.ic_launcher_foreground,
                ),
                StorageCategoryDTO(
                    2,
                    "Овощи и фрукты",
                    R.drawable.ic_launcher_background,
                ),
                StorageCategoryDTO(
                    3,
                    "Рыба",
                    R.drawable.ic_launcher_foreground,
                ),
                StorageCategoryDTO(
                    4,
                    "Выпечка",
                    R.drawable.ic_launcher_background,
                ),
            )
        }

        fun getFakeProduct(parentId: Int): List<StorageProductDTO> {
            val fakeData = listOf(
                StorageProductDTO(
                    0,
                    "Молоко",
                    R.drawable.ic_launcher_background,
                    0,
                ),
                StorageProductDTO(
                    1,
                    "Яйца",
                    R.drawable.ic_launcher_background,
                    0,
                ),
                StorageProductDTO(
                    0,
                    "Мясо",
                    R.drawable.ic_launcher_foreground,
                    1,
                ),
                StorageProductDTO(
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