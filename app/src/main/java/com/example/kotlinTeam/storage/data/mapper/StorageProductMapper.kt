package com.example.kotlinTeam.storage.data.mapper

import com.example.kotlinTeam.storage.data.api.model.StorageProductDto
import com.example.kotlinTeam.storage.data.db.model.StorageProductEntity
import com.example.kotlinTeam.storage.domain.model.StorageProduct
import javax.inject.Inject

class StorageProductMapper @Inject constructor() {
    fun map(
        storageProductDto: List<StorageProductDto>,
        storageProductEntity: List<StorageProductEntity>
    ): List<StorageProduct> {
        return storageProductDto.map {
            StorageProduct(
                id = it.id,
                name = it.name,
                image = it.image,
                parentId = it.parentId,
                selected = storageProductEntity.contains(
                    StorageProductEntity(
                        it.id,
                        it.name,
                        it.parentId
                    )
                ),
            )
        }
    }

    fun mapToEntity(
        storageProduct: StorageProduct,
    ): StorageProductEntity {
        return StorageProductEntity(
            storageProduct.id,
            storageProduct.name,
            storageProduct.parentId
        )
    }

    fun mapEntityListToProductList(
        storageProductEntity: List<StorageProductEntity>
    ):List<StorageProduct> {
        return storageProductEntity.map {
            StorageProduct(
                id = it.id,
                name = it.name,
                image = null,
                parentId = it.parentId,
                selected = true

            )
        }
    }
}
