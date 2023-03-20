package com.example.kotkin_team.storage.domain.use_cases

import com.example.kotkin_team.storage.common.StorageStatuses
import com.example.kotkin_team.storage.domain.model.StorageProduct
import com.example.kotkin_team.storage.domain.repository.StorageRepository
import kotlinx.coroutines.flow.Flow

class StorageGetProduct(
    private val storageRepository: StorageRepository
) {
    operator fun invoke(parentId: Int): Flow<StorageStatuses<List<StorageProduct>>> {
        return storageRepository.getProduct(parentId)
    }
}