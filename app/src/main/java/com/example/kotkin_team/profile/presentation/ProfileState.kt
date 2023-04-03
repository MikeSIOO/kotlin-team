package com.example.kotkin_team.profile.presentation

import com.example.kotkin_team.profile.domain.model.Profile

data class ProfileState(
    val isLoading: Boolean = false,
    val profile: Profile? = null,
    val error: String = ""
)
