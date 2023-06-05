package com.example.kotlinTeam.storage.data.mapper

import com.example.kotlinTeam.common.data.dataSource.model.storage.StorageCategoryDto
import com.example.kotlinTeam.common.data.dataSource.model.storage.StorageProductDto
import com.example.kotlinTeam.storage.data.db.model.StorageProductEntity
import com.example.kotlinTeam.storage.domain.model.StorageDataModel
import javax.inject.Inject

class StorageMapper @Inject constructor() {
    fun mapCategory(input: StorageCategoryDto?): StorageDataModel.StorageCategory {
        return StorageDataModel.StorageCategory(
            id = input?.id,
            title = input?.title,
            image = input?.image
        )
    }

    fun mapProduct(
        storageProductDto: StorageProductDto?,
        storageProductEntity: StorageProductEntity?
    ): StorageDataModel.StorageProduct {
        return StorageDataModel.StorageProduct(
            id = storageProductDto?.id,
            title = storageProductDto?.title,
            image = storageProductDto?.image,
            parentId = storageProductDto?.parentId,
            selected = storageProductEntity != null
        )
    }

    fun mapToProductEntity(
        storageProduct: StorageDataModel.StorageProduct,
    ): StorageProductEntity {
        return StorageProductEntity(
            storageProduct.id.toString(),
            storageProduct.title,
            storageProduct.parentId
        )
    }

    fun mapEntityListToProductList(
        storageProductEntity: List<StorageProductEntity>
    ): List<StorageDataModel.StorageProduct> {
        return storageProductEntity.map {
            StorageDataModel.StorageProduct(
                id = it.id,
                title = it.title,
                image = null,
                parentId = it.parentId,
                selected = true
            )
        }
    }
}
