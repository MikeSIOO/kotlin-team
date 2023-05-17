package com.example.kotlinTeam.storage.domain.useCases

import com.example.kotlinTeam.storage.common.StorageStatuses
import com.example.kotlinTeam.storage.domain.model.StorageDataModel
import com.example.kotlinTeam.storage.domain.repository.StorageRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class StorageSelectProductUseCase @Inject constructor(
    private val storageRepository: StorageRepository
) {
    operator fun invoke(storageProduct: StorageDataModel.StorageProduct): Flow<StorageStatuses<StorageDataModel.StorageProduct>> {
        return storageRepository.selectProduct(storageProduct)
    }
}
