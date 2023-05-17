package com.example.kotlinTeam.storage.domain.useCases

import com.example.kotlinTeam.common.data.repository.FirestoreRepository
import javax.inject.Inject

class StorageGetProductByParentUseCase @Inject constructor(
    private val storageRepository: FirestoreRepository
) {
    operator fun invoke(parentId: String) = storageRepository.getProductByParent(parentId)
}
