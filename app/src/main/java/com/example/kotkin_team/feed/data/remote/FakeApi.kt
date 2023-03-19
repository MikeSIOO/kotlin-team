package com.example.kotkin_team.feed.data.remote

import com.example.kotkin_team.feed.data.Recipe

class FakeApi {
    fun createFakeRecipes(): List<Recipe> {
        val recipes = mutableListOf<Recipe>()
        for (i in 1..30) {
            recipes.add(Recipe(i, "Ризотто", "${30 + i}"))
        }
        return recipes
    }
}