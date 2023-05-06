package com.example.kotlinTeam.feed.presentation

import androidx.paging.PagingData
import com.example.kotlinTeam.common.data.dataSource.model.recipe.RecipeOo

data class FeedState(
    val data: PagingData<RecipeOo>? = null,
    val error: String = "",
    val isLoading: Boolean = false,
    val topPosition: Int = 0,
)
