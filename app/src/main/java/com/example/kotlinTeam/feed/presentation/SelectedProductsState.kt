package com.example.kotlinTeam.feed.presentation

import com.example.kotlinTeam.storage.domain.model.StorageDataModel

data class SelectedProductsState(
    val isLoading: Boolean = true,
    val selectedProducts: List<StorageDataModel.StorageProduct> = emptyList(),
    val error: String? = null
)
