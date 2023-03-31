package com.example.kotkin_team.storage.domain.state

import com.example.kotkin_team.storage.domain.model.StorageProduct

data class StorageProductState(
    val isLoading: Boolean = false,
    val storageProduct: List<StorageProduct> = emptyList(),
    val error: String = ""
)
