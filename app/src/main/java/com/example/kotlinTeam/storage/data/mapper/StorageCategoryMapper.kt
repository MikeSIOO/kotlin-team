package com.example.kotlinTeam.storage.data.mapper

import com.example.kotlinTeam.common.data.dataSource.model.storage.StorageCategoryDto
import com.example.kotlinTeam.storage.domain.model.StorageCategory
import javax.inject.Inject

class StorageCategoryMapper @Inject constructor() {
    fun map(input: StorageCategoryDto): StorageCategory {
        return StorageCategory(
            id = input.id,
            title = input.title,
            image = input.image
        )
    }
}
