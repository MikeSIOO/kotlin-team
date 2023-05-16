package com.example.kotlinTeam.feed.presentation

import com.example.kotlinTeam.storage.domain.model.StorageProduct

data class SelectedProductsState(
    val isLoading: Boolean = true,
    val selectedProducts: List<StorageProduct> = emptyList(),
    val error: String? = null
)
