package com.example.kotkin_team.profile.data.data_source.model

import com.example.kotkin_team.profile.domain.model.Recipe

data class RecipeDto(
    val id: Long,
    val title: String = "",
    val image: String? = null,
) {
    fun toRecipe(): Recipe {
        return Recipe(
            id = id,
            title = title,
            image = image
        )
    }
}
