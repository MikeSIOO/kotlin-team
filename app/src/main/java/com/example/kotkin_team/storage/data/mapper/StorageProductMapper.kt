package com.example.kotkin_team.storage.data.mapper

import com.example.kotkin_team.storage.data.api.model.StorageProductDto
import com.example.kotkin_team.storage.domain.model.StorageProduct

class StorageProductMapper {
    fun map(input: List<StorageProductDto>): List<StorageProduct> {
        return input.map {
            StorageProduct(
                id = it.id,
                name = it.name,
                image = it.image,
                parentId = it.parentId,
                selected = false, // TODO брать из локальной БД
            )
        }
    }
}