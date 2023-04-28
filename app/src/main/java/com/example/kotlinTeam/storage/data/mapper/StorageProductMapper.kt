package com.example.kotlinTeam.storage.data.mapper

import com.example.kotlinTeam.common.data.dataSource.model.storage.StorageProductDto
import com.example.kotlinTeam.storage.data.db.model.StorageProductEntity
import com.example.kotlinTeam.storage.domain.model.StorageProduct
import javax.inject.Inject

class StorageProductMapper @Inject constructor() {
    fun map(
        storageProductDto: StorageProductDto,
        storageProductEntity: List<StorageProductEntity>?
    ): StorageProduct {
        if (storageProductEntity != null) {
            return StorageProduct(
                id = storageProductDto.id,
                title = storageProductDto.title,
                image = storageProductDto.image,
                parentId = storageProductDto.parentId,
                selected = storageProductEntity.contains(
                    StorageProductEntity(
                        storageProductDto.id!!,
                        storageProductDto.title,
                        storageProductDto.parentId
                    )
                ),
            )
        } else {
            return StorageProduct(
                id = storageProductDto.id,
                title = storageProductDto.title,
                image = storageProductDto.image,
                parentId = storageProductDto.parentId,
                selected = false
            )
        }
    }
//    fun map(
//        storageProductDto: List<StorageProductDto>,
//        storageProductEntity: List<StorageProductEntity>
//    ): List<StorageProduct> {
//        return storageProductDto.map {
//            StorageProduct(
//                id = it.id,
//                name = it.name,
//                image = it.image,
//                parentId = it.parentId,
//                selected = storageProductEntity.contains(
//                    StorageProductEntity(
//                        it.id,
//                        it.name,
//                        it.parentId
//                    )
//                ),
//            )
//        }
//    }

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
