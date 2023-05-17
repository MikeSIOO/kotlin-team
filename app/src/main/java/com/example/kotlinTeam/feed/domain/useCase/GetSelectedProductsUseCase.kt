package com.example.kotlinTeam.feed.domain.useCase

import com.example.kotlinTeam.storage.common.StorageStatuses
import com.example.kotlinTeam.storage.domain.model.StorageDataModel
import com.example.kotlinTeam.storage.domain.repository.StorageRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetSelectedProductsUseCase @Inject constructor(
    private val storageRepository: StorageRepository
) {
    operator fun invoke(): Flow<StorageStatuses<List<StorageDataModel.StorageProduct>>> {
        return storageRepository.getSelectedProducts()
    }
}
