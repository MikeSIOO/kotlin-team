package com.example.kotlinTeam.common.data.dataSource.model.recipe

import com.example.kotlinTeam.profile.domain.model.MadeRecipe

data class RecipeOo(
    val id: String? = null,
    val title: String? = null,
    val image: String? = null,
    val cookingMinutes: Int? = null,
    val difficulty: String? = null,
    val cuisines: List<CuisineDto> = emptyList(),
    val diets: List<DietDto> = emptyList(),
    val description: String? = null,
    val servings: Int? = null,
    val ingredients: List<IngredientOo> = emptyList(),
    val instructions: List<StepOo> = emptyList(),
) {
    fun toMadeRecipe(): MadeRecipe {
        return MadeRecipe(
            id = id!!,
            title = title!!,
            image = image
        )
    }
}
