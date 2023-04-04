package com.example.kotlinTeam.feed.domain.repository

import com.example.kotlinTeam.feed.data.Recipe

interface FeedRepository {
    suspend fun getRecipes(): List<Recipe>
}
