package com.example.kotkin_team.storage.domain.state

import com.example.kotkin_team.storage.domain.model.StorageCategory

data class StorageCategoryState(
    val isLoading: Boolean = false,
    val storageCategory: List<StorageCategory> = emptyList(),
    val error: String = ""
)