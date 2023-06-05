package com.example.kotlinTeam.storage.domain.useCases

import com.example.kotlinTeam.common.data.repository.FirestoreRepository
import javax.inject.Inject

class StorageGetCategoryUseCase @Inject constructor(
    private val storageRepository: FirestoreRepository
) {
    operator fun invoke() = storageRepository.getCategory()
}
