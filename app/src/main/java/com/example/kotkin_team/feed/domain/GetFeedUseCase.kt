package com.example.kotkin_team.feed.domain

import com.example.kotkin_team.feed.data.Recipe

class GetFeedUseCase(private val repo: FeedRepositoryImpl) {
//    private val repo = FeedRepositoryImpl()
    suspend fun getRecipes(): List<Recipe> {
        return repo.getRecipes()
    }
}
