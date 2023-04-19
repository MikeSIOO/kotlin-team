package com.example.kotlinTeam.common.data.dataSource.model.recipe

import com.google.firebase.firestore.DocumentReference

data class RecipeDto(
    val id: String? = null,
    val title: String? = null,
    val image: String? = null,
    val cookingMinutes: Int? = null,
    val difficulty: String? = null,
    val cuisines: List<DocumentReference>? = null,
    val diets: List<DocumentReference>? = null,
    val description: String? = null,
    val servings: Int? = null,
    val ingredients: Map<String, DocumentReference>? = null,
    val instructions: Map<String, String>? = null
)
