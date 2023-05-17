package com.example.kotlinTeam.storage.domain.useCases

import com.example.kotlinTeam.common.data.repository.FirestoreRepository
import javax.inject.Inject

class StorageGetProductByTitleUseCase @Inject constructor(
    private val storageRepository: FirestoreRepository
) {
    operator fun invoke(title: String) = storageRepository.getProductByTitle(title)
}
