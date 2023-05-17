package com.example.kotlinTeam.storage.domain.state

import androidx.paging.PagingData
import com.example.kotlinTeam.storage.domain.model.StorageDataModel

data class StorageState(
    val isLoading: Boolean = false,
    val storageData: PagingData<StorageDataModel>? = null,
    val error: String = ""
)
