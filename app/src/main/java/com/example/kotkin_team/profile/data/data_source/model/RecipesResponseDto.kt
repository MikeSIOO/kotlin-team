package com.example.kotkin_team.profile.data.data_source.model


data class RecipesResponseDto(
    val status: String,
    val totalResults: Int,
    val recipes: List<RecipeDto>
)