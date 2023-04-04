package com.example.kotlinTeam.profile.domain.useCases

import androidx.paging.PagingData
import com.example.kotlinTeam.common.data.dataSource.model.recipe.RecipeOo
import com.example.kotlinTeam.common.data.repository.FirestoreRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetMadeRecipes @Inject constructor(
    private val repository: FirestoreRepository
) {

    operator fun invoke(id: Int): Flow<PagingData<RecipeOo>> {
        return repository.getRecipes(id)
    }
}
