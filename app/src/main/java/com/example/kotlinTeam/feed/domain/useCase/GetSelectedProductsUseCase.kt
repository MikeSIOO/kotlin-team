package com.example.kotlinTeam.feed.domain.useCase

import com.example.kotlinTeam.storage.common.StorageStatuses
import com.example.kotlinTeam.storage.domain.model.StorageProduct
import com.example.kotlinTeam.storage.domain.repository.StorageRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSelectedProductsUseCase @Inject constructor(
    private val storageRepository: StorageRepository
) {
    operator fun invoke(): Flow<StorageStatuses<List<StorageProduct>>> {
        return storageRepository.getSelectedProducts()
    }
}