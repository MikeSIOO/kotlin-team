package com.example.kotkin_team.profile.domain.use_cases

import javax.inject.Inject

data class ProfileUseCases @Inject constructor(
    val getProfile: GetProfile,
    val getMadeRecipes: GetMadeRecipes
)
