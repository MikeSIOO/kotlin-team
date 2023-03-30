package com.example.kotkin_team.profile.presentation

import com.example.kotkin_team.profile.domain.model.MadeRecipe

sealed class ProfileFragmentEvents {
    object LoadMadeRecipes : ProfileFragmentEvents()
    object LoadProfile : ProfileFragmentEvents()
    data class LoadRecipe(val madeRecipe: MadeRecipe) : ProfileFragmentEvents()
}
