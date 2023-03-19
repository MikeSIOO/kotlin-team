package com.example.kotkin_team.feed.domain

import com.example.kotkin_team.feed.domain.repository.FeedRepository
import com.example.kotkin_team.feed.data.Recipe

class GetFeedUseCase(private val repo: FeedRepository) {
    suspend fun getRecipes(): List<Recipe> {
        return repo.getRecipes()
    }
}
