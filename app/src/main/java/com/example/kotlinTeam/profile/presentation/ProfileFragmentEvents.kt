package com.example.kotlinTeam.profile.presentation

import com.example.kotlinTeam.profile.domain.model.MadeRecipe

sealed class ProfileFragmentEvents {
    object LoadMadeRecipes : ProfileFragmentEvents()
    object LoadProfile : ProfileFragmentEvents()
    data class LoadRecipe(val madeRecipe: MadeRecipe) : ProfileFragmentEvents()
}
