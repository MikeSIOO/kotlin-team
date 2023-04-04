package com.example.kotkin_team.common.data.data_source.model.recipe

import com.example.kotkin_team.profile.domain.model.MadeRecipe

data class RecipeOo(
    val id: Long? = null,
    val title: String? = null,
    val image: String? = null,
    val cookingMinutes: Int? = null,
    val cuisines: List<CuisineDto?> = emptyList(),
    val diets: List<DietDto?> = emptyList(),
    val servings: Int? = null,
    val ingredients: List<IngredientDto?> = emptyList(),
    val instructions: Map<String, String> = emptyMap()
) {
    fun toMadeRecipe(): MadeRecipe {
        return MadeRecipe(
            id = id ?: -1,
            title = title ?: "111",
            image = image
        )
    }
}
