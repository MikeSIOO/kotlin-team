package com.example.kotkin_team.profile.domain.use_cases

import androidx.paging.PagingData
import com.example.kotkin_team.common.data.data_source.model.recipe.RecipeDto
import com.example.kotkin_team.profile.domain.repository.ProfileRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetMadeRecipes @Inject constructor(
    private val repository: ProfileRepository
) {

    operator fun invoke(id: Int): Flow<PagingData<RecipeDto>> {
        return repository.getRecipes(id)
    }
}
