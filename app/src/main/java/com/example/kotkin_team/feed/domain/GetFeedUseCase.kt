package com.example.kotkin_team.feed.domain

import com.example.kotkin_team.feed.domain.repository.FeedRepository
import com.example.kotkin_team.feed.data.Recipe
import javax.inject.Inject

class GetFeedUseCase @Inject constructor(
    private val repo: FeedRepository
) {
    suspend fun getRecipes(): List<Recipe> {
        return repo.getRecipes()
    }
}
