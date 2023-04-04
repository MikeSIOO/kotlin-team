package com.example.kotlinTeam.storage.domain.useCases

import com.example.kotlinTeam.storage.common.StorageStatuses
import com.example.kotlinTeam.storage.domain.model.StorageProduct
import com.example.kotlinTeam.storage.domain.repository.StorageRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class StorageGetProductUseCase @Inject constructor(
    private val storageRepository: StorageRepository
) {
    operator fun invoke(parentId: Int): Flow<StorageStatuses<List<StorageProduct>>> {
        return storageRepository.getProduct(parentId)
    }
}
