package com.example.kotlinTeam.feed.domain.useCase

import com.example.kotlinTeam.common.data.repository.FirestoreRepository
import com.example.kotlinTeam.storage.domain.model.StorageProduct
import javax.inject.Inject

class GetFeedUseCase @Inject constructor(
    private val repo: FirestoreRepository
) {
    suspend operator fun invoke(products: List<StorageProduct>) = repo.getRecipesByProducts(
        products
    )
}
