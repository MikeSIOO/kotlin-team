package com.example.kotlinTeam.feed.presentation

data class FeedEndState(
    val isEndOfFeed: Boolean = false,
    val allRecipesWereShown: Boolean = false,
    val recipesWereFound: Boolean = false,
)
