package com.example.kotkin_team.profile.domain.repository

import com.example.kotkin_team.profile.common.Resource
import com.example.kotkin_team.profile.domain.model.Recipe
import com.example.kotkin_team.profile.domain.model.Profile
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {

    fun getProfile(id: Int): Flow<Resource<Profile>>

    fun getMadeRecipes(profile: Profile): Flow<Resource<List<Recipe>>>
}