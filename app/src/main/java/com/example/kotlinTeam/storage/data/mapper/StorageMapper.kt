package com.example.kotlinTeam.storage.data.mapper

import com.example.kotlinTeam.common.data.dataSource.model.storage.StorageCategoryDto
import com.example.kotlinTeam.common.data.dataSource.model.storage.StorageProductDto
import com.example.kotlinTeam.storage.data.db.model.StorageProductEntity
import com.example.kotlinTeam.storage.domain.model.StorageCategory
import com.example.kotlinTeam.storage.domain.model.StorageProduct
import javax.inject.Inject

class StorageMapper @Inject constructor() {
    fun mapCategory(input: StorageCategoryDto?): StorageCategory {
        return StorageCategory(
            id = input?.id,
            title = input?.title,
            image = input?.image
        )
    }

    fun mapProduct(
        storageProductDto: StorageProductDto?,
        storageProductEntity: StorageProductEntity?
    ): StorageProduct {
        return StorageProduct(
            id = storageProductDto?.id,
            title = storageProductDto?.title,
            image = storageProductDto?.image,
            parentId = storageProductDto?.parentId,
            selected = storageProductEntity != null
        )
    }

    fun mapToProductEntity(
        storageProduct: StorageProduct,
    ): StorageProductEntity {
        return StorageProductEntity(
            storageProduct.id.toString(),
            storageProduct.title,
            storageProduct.parentId
        )
    }
}
