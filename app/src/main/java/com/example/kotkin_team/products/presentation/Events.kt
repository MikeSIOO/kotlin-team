package com.example.kotkin_team.products.presentation

sealed class Events {
    data class LoadCategory(val page: Int) : Events()
}