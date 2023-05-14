package com.example.kotlinTeam.profile.domain.useCases

import androidx.paging.PagingData
import com.example.kotlinTeam.common.data.dataSource.model.recipe.RecipeOo
import com.example.kotlinTeam.common.data.repository.AuthRepository
import com.example.kotlinTeam.common.data.repository.FirestoreRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetMadeRecipes @Inject constructor(
    private val repository: FirestoreRepository,
    private val authRepository: AuthRepository
) {

    operator fun invoke(): Flow<PagingData<RecipeOo>> {
        return repository.getRecipesByUserId(authRepository.getCurrentUser()?.uid!!)
    }
}
