package com.example.kotkin_team.profile.domain.use_cases

import com.example.kotkin_team.profile.common.Resource
import com.example.kotkin_team.profile.domain.model.Recipe
import com.example.kotkin_team.profile.domain.model.Profile
import com.example.kotkin_team.profile.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow

class GetMadeRecipes(
    private val repository: ProfileRepository
) {

    operator fun invoke(profile: Profile): Flow<Resource<List<Recipe>>> {
        return repository.getMadeRecipes(profile)
    }
}