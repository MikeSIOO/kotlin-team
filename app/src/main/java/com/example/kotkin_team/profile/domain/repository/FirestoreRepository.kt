package com.example.kotkin_team.profile.domain.repository

import androidx.paging.PagingData
import com.example.kotkin_team.profile.common.Resource
import com.example.kotkin_team.common.data.data_source.model.recipe.RecipeDto
import com.example.kotkin_team.profile.domain.model.Profile
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {

    fun getProfile(id: Int): Flow<Resource<Profile>>

    fun getRecipes(id: Int): Flow<PagingData<RecipeDto>>
}
