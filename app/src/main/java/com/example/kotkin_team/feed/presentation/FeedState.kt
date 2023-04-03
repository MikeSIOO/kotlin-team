package com.example.kotkin_team.feed.presentation

import com.example.kotkin_team.feed.data.Recipe
import com.example.kotkin_team.feed.domain.FeedLoadingState

data class FeedState(val loadingState: FeedLoadingState = FeedLoadingState.LOADED,
                     val isMoreInfoButtonClicked: Boolean = false,
                     val recipeList: List<Recipe> = emptyList(),
                     val currentRecipe: Recipe? = null,
)