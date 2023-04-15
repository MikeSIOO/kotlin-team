package com.example.kotlinTeam.profile.domain.model

data class Profile(
    val id: String,
    val name: String,
    val secondName: String?,
    val image: String,
    val madeRecipes: Map<Long, MadeRecipe>? = emptyMap()
)
