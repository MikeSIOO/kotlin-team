package com.example.kotlinTeam.storage.domain.state

import com.example.kotlinTeam.storage.domain.model.StorageDataModel

data class StorageSelectProductState(
    val isLoading: Boolean = false,
    val storageProduct: StorageDataModel.StorageProduct? = null,
    val error: String = ""
)
