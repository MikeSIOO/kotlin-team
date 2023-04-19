package com.example.kotlinTeam.feed.domain.useCase

import com.example.kotlinTeam.common.data.repository.FirestoreRepository
import javax.inject.Inject

class GetFeedUseCase @Inject constructor(
    private val repo: FirestoreRepository
) {
    operator fun invoke() = repo.getRecipes(null)
}
