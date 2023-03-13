package com.example.kotkin_team.feed.data

interface FeedRepository {
    suspend fun getRecipes(): List<Recipe>
}
