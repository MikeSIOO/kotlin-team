package com.example.kotlinTeam.storage.domain.repository

import com.example.kotlinTeam.storage.common.StorageStatuses
import com.example.kotlinTeam.storage.domain.model.StorageProduct
import kotlinx.coroutines.flow.Flow

interface StorageRepository {
    fun selectProduct(storageProduct: StorageProduct): Flow<StorageStatuses<StorageProduct>>
}
