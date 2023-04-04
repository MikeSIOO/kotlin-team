package com.example.kotlinTeam.storage.domain.state

import com.example.kotlinTeam.storage.domain.model.StorageProduct

data class StorageSelectProductState(
    val isLoading: Boolean = false,
    val storageProduct: StorageProduct? = null,
    val error: String = ""
)
