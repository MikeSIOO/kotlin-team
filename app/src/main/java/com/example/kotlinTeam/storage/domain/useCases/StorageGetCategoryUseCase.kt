package com.example.kotlinTeam.storage.domain.useCases

import com.example.kotlinTeam.storage.common.StorageStatuses
import com.example.kotlinTeam.storage.domain.model.StorageCategory
import com.example.kotlinTeam.storage.domain.repository.StorageRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class StorageGetCategoryUseCase @Inject constructor(
    private val storageRepository: StorageRepository
) {
    operator fun invoke(): Flow<StorageStatuses<List<StorageCategory>>> {
        return storageRepository.getCategory()
    }
}
