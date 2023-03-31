package com.example.kotkin_team.storage.domain.use_cases

import com.example.kotkin_team.storage.common.StorageStatuses
import com.example.kotkin_team.storage.domain.model.StorageCategory
import com.example.kotkin_team.storage.domain.repository.StorageRepository
import kotlinx.coroutines.flow.Flow

class StorageGetCategory(
    private val storageRepository: StorageRepository
) {
    operator fun invoke(): Flow<StorageStatuses<List<StorageCategory>>> {
        return storageRepository.getCategory()
    }
}
