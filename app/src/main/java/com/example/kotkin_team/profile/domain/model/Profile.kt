package com.example.kotkin_team.profile.domain.model

data class Profile(
    val id: Int,
    val name: String,
    val secondName: String?,
    val image: String? = null,
    val diets: List<String> = emptyList()
)
