package com.example.kotlinTeam.profile.presentation

import androidx.paging.PagingData
import com.example.kotlinTeam.profile.domain.model.MadeRecipe
import com.example.kotlinTeam.profile.domain.model.Profile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class ProfileState(
    val isLoading: Boolean = false,
    val profile: Profile? = null,
    val error: String = "",
    val madeRecipes: Flow<PagingData<MadeRecipe>> = emptyFlow()
)
