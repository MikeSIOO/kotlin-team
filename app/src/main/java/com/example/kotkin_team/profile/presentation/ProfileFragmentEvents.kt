package com.example.kotkin_team.profile.presentation

import com.example.kotkin_team.profile.domain.model.Recipe
import com.example.kotkin_team.profile.domain.model.Profile

sealed class ProfileFragmentEvents {
    data class LoadMadeRecipes(val profile: Profile): ProfileFragmentEvents()
    data class LoadProfile(val id: Int): ProfileFragmentEvents()
    data class LoadRecipe(val recipe: Recipe): ProfileFragmentEvents()
}
