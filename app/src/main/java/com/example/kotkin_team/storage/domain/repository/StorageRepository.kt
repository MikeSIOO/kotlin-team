package com.example.kotkin_team.storage.domain.repository

import com.example.kotkin_team.storage.common.StorageStatuses
import com.example.kotkin_team.storage.domain.model.StorageCategory
import com.example.kotkin_team.storage.domain.model.StorageProduct
import kotlinx.coroutines.flow.Flow

interface StorageRepository {
    fun getCategory(): Flow<StorageStatuses<List<StorageCategory>>>

    fun getProduct(parentId: Int): Flow<StorageStatuses<List<StorageProduct>>>

    fun selectProduct(storageProduct: StorageProduct): Flow<StorageStatuses<StorageProduct>>
}
