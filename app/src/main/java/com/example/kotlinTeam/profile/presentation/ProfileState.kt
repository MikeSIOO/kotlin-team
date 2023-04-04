package com.example.kotlinTeam.profile.presentation

import com.example.kotlinTeam.profile.domain.model.Profile

data class ProfileState(
    val isLoading: Boolean = false,
    val profile: Profile? = null,
    val error: String = ""
)
