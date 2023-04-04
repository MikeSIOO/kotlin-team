package com.example.kotlinTeam.feed.data

data class Recipe(
    val id: Int,
    val title: String,
    val time: String,
    val difficulty: String,
    val cuisine: String,
    val ingredients: List<Ingredient>,
    val steps: List<Step>,
)
