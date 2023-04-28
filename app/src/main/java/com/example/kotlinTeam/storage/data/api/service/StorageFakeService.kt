package com.example.kotlinTeam.storage.data.api.service

import com.example.kotlinTeam.storage.data.api.model.StorageProductDto
import com.example.kotlinTeam.storage.data.api.model.StorageProductResponseDto
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.delay

// фейковый сервис отправления запросов
@Singleton
class StorageFakeService @Inject constructor() {
    suspend fun getProduct(parentId: Int): StorageProductResponseDto {
        delay(1000L)
        val fakeProductDTO = getFakeProduct(parentId)
        return StorageProductResponseDto(
            status = "Success",
            count = fakeProductDTO.size,
            product = fakeProductDTO
        )
    }

    companion object {
        fun getFakeProduct(parentId: Int): List<StorageProductDto> {
            val fakeData = listOf(
                StorageProductDto(
                    0,
                    "Молоко",
                    "https://calorizator.ru/sites/default/files/imagecache/product_512/product/milk-33.jpg",
                    0,
                ),
                StorageProductDto(
                    1,
                    "Яйца",
                    "https://calorizator.ru/sites/default/files/imagecache/product_512/product/milk-33.jpg",
                    0,
                ),
                StorageProductDto(
                    2,
                    "Мясо",
                    "https://calorizator.ru/sites/default/files/imagecache/product_512/product/milk-33.jpg",
                    1,
                ),
                StorageProductDto(
                    3,
                    "Птица",
                    "https://calorizator.ru/sites/default/files/imagecache/product_512/product/milk-33.jpg",
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
