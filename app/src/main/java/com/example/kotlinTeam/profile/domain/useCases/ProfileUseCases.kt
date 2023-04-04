package com.example.kotlinTeam.profile.domain.useCases

import javax.inject.Inject

data class ProfileUseCases @Inject constructor(
    val getProfile: GetProfile,
    val getMadeRecipes: GetMadeRecipes
)
