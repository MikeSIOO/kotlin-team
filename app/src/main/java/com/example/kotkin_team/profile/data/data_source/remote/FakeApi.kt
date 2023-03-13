package com.example.kotkin_team.profile.data.data_source.remote

import com.example.kotkin_team.profile.data.data_source.model.RecipeDto
import com.example.kotkin_team.profile.data.data_source.model.RecipesResponseDto
import com.example.kotkin_team.profile.domain.model.Profile
import kotlinx.coroutines.delay

class FakeApi {

    suspend fun getMadeRecipes(profile: Profile): RecipesResponseDto {
        delay(2000L)
        val fakeRecipes = mockRecipes()
        return RecipesResponseDto(
            status = "Success",
            totalResults = fakeRecipes.size,
            recipes = fakeRecipes
        )
    }

    suspend fun getProfile(id: Int): Profile {
        return Profile(
            id = 1,
            name = "Maria",
            secondName = "Kopernik"
        )
    }

    companion object {

        const val DEFAULT_PAGE_SIZE = 20
        const val MAX_PAGE_SIZE = 20
    }

    private fun mockRecipes(): List<RecipeDto> {
        return listOf(
            RecipeDto(
                id = 13,
                title = "Potato Chips",
                image = null
            ),
            RecipeDto(
                id = 2,
                title = "Tomatoes",
                image = null
            ),
            RecipeDto(
                id = 51,
                title = "Chicken",
                image = null
            ),
            RecipeDto(
                id = 44,
                title = "Beef",
                image = null
            ),
            RecipeDto(
                id = 1,
                title = "Borsch",
                image = null
            ),
            RecipeDto(
                id = 898,
                title = "Energy Drink",
                image = null
            ),
            RecipeDto(
                id = 144,
                title = "Yogurt",
                image = null
            ),
            RecipeDto(
                id = 11,
                title = "Meat Loaf",
                image = null
            ),
            RecipeDto(
                id = 87,
                title = "Fish'n Chips",
                image = null
            ),
            RecipeDto(
                id = 86,
                title = "Carrot",
                image = null
            ),
            RecipeDto(
                id = 85,
                title = "Cucumber",
                image = null
            ),
            RecipeDto(
                id = 84,
                title = "Pasta",
                image = null
            ),
            RecipeDto(
                id = 83,
                title = "Fries",
                image = null
            ),
            RecipeDto(
                id = 82,
                title = "Lobster",
                image = null
            ),
            RecipeDto(
                id = 80,
                title = "Seaweed",
                image = null
            )
        )
    }
}
