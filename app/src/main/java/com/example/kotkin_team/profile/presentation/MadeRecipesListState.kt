package com.example.kotkin_team.profile.presentation

import com.example.kotkin_team.profile.domain.model.Recipe

data class MadeRecipesListState(
    val isLoading: Boolean = false,
    val madeRecipes: List<Recipe> = emptyList(),
    val error: String = ""
)
