package com.example.kotkin_team.feed.domain

import com.example.kotkin_team.feed.data.FeedRepository
import com.example.kotkin_team.feed.data.Recipe
import kotlinx.coroutines.delay

class FeedRepositoryImpl : FeedRepository {
    override suspend fun getRecipes(): List<Recipe> {
        val data = createFakeRecipes()
        delay(1000)
        return data
    }

    private fun createFakeRecipes(): List<Recipe> {
        val recipes = mutableListOf<Recipe>()
        for (i in 1..30) {
            recipes.add(Recipe(i, "Ризотто", "${30 + i}"))
        }
        return recipes
    }
}
