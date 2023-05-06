package com.example.kotlinTeam.storage.data.mapper

import android.util.Log
import com.example.kotlinTeam.common.data.dataSource.model.storage.StorageProductDto
import com.example.kotlinTeam.storage.data.db.model.StorageProductEntity
import com.example.kotlinTeam.storage.domain.model.StorageProduct
import javax.inject.Inject

class StorageProductMapper @Inject constructor() {
    fun map(
        storageProductDto: StorageProductDto,
        storageProductEntity: StorageProductEntity?
    ): StorageProduct {
        Log.i("!@#", storageProductEntity.toString())
        return StorageProduct(
            id = storageProductDto.id,
            title = storageProductDto.title,
            image = storageProductDto.image,
            parentId = storageProductDto.parentId,
            selected = storageProductEntity != null
        )
    }

    fun mapToEntity(
        storageProduct: StorageProduct,
    ): StorageProductEntity {
        return StorageProductEntity(
            storageProduct.id!!,
            storageProduct.title,
            storageProduct.parentId
        )
    }
}
