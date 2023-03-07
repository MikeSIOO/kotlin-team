package com.example.kotkin_team.products.domain

sealed interface ProductsScreenEvent {
    object OnNextClick : ProductsScreenEvent
    data class OnPageSelect(val id: String) : ProductsScreenEvent
}