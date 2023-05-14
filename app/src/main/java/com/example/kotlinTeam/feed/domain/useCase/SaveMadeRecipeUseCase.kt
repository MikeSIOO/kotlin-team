package com.example.kotlinTeam.feed.domain.useCase

import com.example.kotlinTeam.common.data.repository.AuthRepository
import com.example.kotlinTeam.common.data.repository.FirestoreRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SaveMadeRecipeUseCase @Inject constructor (
    private val repository: FirestoreRepository,
    private val authRepository: AuthRepository

) {
    suspend operator fun invoke(recipeId: String) = withContext(Dispatchers.IO) { repository.saveMadeRecipe(recipeId, authRepository.getCurrentUser()!!.uid) }
}