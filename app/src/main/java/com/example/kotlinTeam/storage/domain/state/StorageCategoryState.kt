package com.example.kotlinTeam.storage.domain.state

import androidx.paging.PagingData
import com.example.kotlinTeam.storage.domain.model.StorageCategory

data class StorageCategoryState(
    val isLoading: Boolean = false,
    val storageCategory: PagingData<StorageCategory>? = null,
    val error: String = ""
)
