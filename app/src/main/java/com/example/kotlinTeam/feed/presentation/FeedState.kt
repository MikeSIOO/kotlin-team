package com.example.kotlinTeam.feed.presentation

import com.example.kotlinTeam.feed.data.Recipe
import com.example.kotlinTeam.feed.domain.FeedLoadingState

data class FeedState(
    val loadingState: FeedLoadingState = FeedLoadingState.LOADED,
    val isMoreInfoButtonClicked: Boolean = false,
    val recipeList: List<Recipe> = emptyList(),
    val currentRecipe: Recipe? = null,
)
