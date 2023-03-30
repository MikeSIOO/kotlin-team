package com.example.kotkin_team.common.data.data_source.model.recipe

import com.example.kotkin_team.profile.domain.model.MadeRecipe
import com.google.firebase.firestore.DocumentReference

data class RecipeDto(
    val id: Long? = null,
    val title: String? = null,
    val image: String? = null,
    val cookingMinutes: Int? = null,
    val cuisines: List<DocumentReference>? = null,
    val diets: List<DocumentReference>? = null,
    val servings: Int? = null,
    val ingredients: List<DocumentReference>? = null,
    val instructions: Map<String, String>? = null
) {
    fun toMadeRecipe(): MadeRecipe {
        return MadeRecipe(
            id = id ?: -1,
            title = title ?: "111",
            image = image
        )
    }
}
