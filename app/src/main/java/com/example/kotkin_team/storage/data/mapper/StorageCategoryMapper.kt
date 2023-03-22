package com.example.kotkin_team.storage.data.mapper

import com.example.kotkin_team.storage.data.api.model.StorageCategoryDto
import com.example.kotkin_team.storage.domain.model.StorageCategory

class StorageCategoryMapper {
    fun map(input: List<StorageCategoryDto>): List<StorageCategory> {
        return input.map {
            StorageCategory(
                id = it.id,
                name = it.name,
                image = it.image
            )
        }
    }
}