package com.example.kotlinTeam.feed.domain

import com.example.kotlinTeam.feed.data.Recipe
import com.example.kotlinTeam.feed.domain.repository.FeedRepository
import javax.inject.Inject

class GetFeedUseCase @Inject constructor(
    private val repo: FeedRepository
) {
    suspend fun getRecipes(): List<Recipe> {
        return repo.getRecipes()
    }
}
