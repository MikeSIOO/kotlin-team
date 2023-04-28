package com.example.kotlinTeam.storage.domain.state

import androidx.paging.PagingData
import com.example.kotlinTeam.storage.domain.model.StorageProduct

data class StorageProductState(
    val isLoading: Boolean = false,
    val storageProduct: PagingData<StorageProduct>? = null,
    val error: String = ""
)
