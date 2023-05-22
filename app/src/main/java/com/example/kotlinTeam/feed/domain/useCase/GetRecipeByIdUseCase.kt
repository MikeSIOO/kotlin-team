package com.example.kotlinTeam.feed.domain.useCase

import com.example.kotlinTeam.common.data.dataSource.model.recipe.RecipeOo
import com.example.kotlinTeam.common.data.repository.FirestoreRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetRecipeByIdUseCase @Inject constructor(
    private val repo: FirestoreRepository
) {
    suspend operator fun invoke(recipeId: String): RecipeOo {
        return withContext(Dispatchers.IO) { repo.getRecipeById(recipeId) }
    }
}