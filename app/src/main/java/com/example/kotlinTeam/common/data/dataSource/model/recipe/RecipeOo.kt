package com.example.kotlinTeam.common.data.dataSource.model.recipe

import com.example.kotlinTeam.profile.domain.model.MadeRecipe

data class RecipeOo(
    val id: String? = null,
    val title: String? = null,
    val image: String? = null,
    val cookingMinutes: Int? = null,
    val cuisines: List<CuisineDto> = emptyList(),
    val diets: List<DietDto> = emptyList(),
    val servings: Int? = null,
    val ingredientsMap: Map<String, IngredientDto> = emptyMap(),
    val instructions: Map<String, String> = emptyMap()
) {
    fun toMadeRecipe(): MadeRecipe {
        return MadeRecipe(
            id = id ?: "no id",
            title = title ?: "no title",
            image = image
        )
    }
}
