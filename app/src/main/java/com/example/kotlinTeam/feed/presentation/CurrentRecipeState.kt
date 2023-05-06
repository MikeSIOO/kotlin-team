package com.example.kotlinTeam.feed.presentation

import com.example.kotlinTeam.common.data.dataSource.model.recipe.RecipeOo

data class CurrentRecipeState(
    val currentRecipe: RecipeOo? = null,
    val isMoreInfoButtonClicked: Boolean = false,
)
