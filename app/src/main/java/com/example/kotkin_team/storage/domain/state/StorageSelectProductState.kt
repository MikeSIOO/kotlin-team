package com.example.kotkin_team.storage.domain.state

import com.example.kotkin_team.storage.domain.model.StorageProduct

data class StorageSelectProductState(
    val isLoading: Boolean = false,
    val storageProduct: StorageProduct? = null,
    val error: String = ""
)