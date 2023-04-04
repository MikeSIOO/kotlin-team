package com.example.kotlinTeam.storage.domain.state

import com.example.kotlinTeam.storage.domain.model.StorageProduct

data class StorageProductState(
    val isLoading: Boolean = false,
    val storageProduct: List<StorageProduct> = emptyList(),
    val error: String = ""
)
