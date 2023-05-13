package com.example.kotlinTeam.storage.domain.state

import androidx.paging.PagingData
import com.example.kotlinTeam.storage.domain.model.StorageCategory
import com.example.kotlinTeam.storage.domain.model.StorageDataModel

data class StorageCategoryState(
    val isLoading: Boolean = false,
    val storageCategory: PagingData<StorageDataModel>? = null,
    val error: String = ""
)
