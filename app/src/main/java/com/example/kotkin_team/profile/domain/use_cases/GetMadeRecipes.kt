package com.example.kotkin_team.profile.domain.use_cases

import androidx.paging.PagingData
import com.example.kotkin_team.common.data.data_source.model.recipe.RecipeOo
import com.example.kotkin_team.common.data.repository.FirestoreRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetMadeRecipes @Inject constructor(
    private val repository: FirestoreRepository
) {

    operator fun invoke(id: Int): Flow<PagingData<RecipeOo>> {
        return repository.getRecipes(id)
    }
}
