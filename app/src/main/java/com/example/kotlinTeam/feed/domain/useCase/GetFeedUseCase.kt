package com.example.kotlinTeam.feed.domain.useCase

import com.example.kotlinTeam.common.data.repository.FirestoreRepository
import com.example.kotlinTeam.storage.domain.model.StorageDataModel
import javax.inject.Inject

class GetFeedUseCase @Inject constructor(
    private val repo: FirestoreRepository
) {
    suspend operator fun invoke(products: List<StorageDataModel.StorageProduct>) = repo.getRecipesByProducts(
        products
    )
    operator fun invoke() = repo.getRecipes()
}
