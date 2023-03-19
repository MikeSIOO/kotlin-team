package com.example.kotkin_team.feed.domain.repository

import com.example.kotkin_team.feed.data.Recipe

interface FeedRepository {
    suspend fun getRecipes(): List<Recipe>
}