package com.example.kotkin_team.products.presentation

import com.example.kotkin_team.products.domain.model.Category

data class State(
    val isLoading: Boolean = false,
    val category: List<Category> = emptyList(),
    val error: String = ""
)