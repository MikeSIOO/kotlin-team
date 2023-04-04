package com.example.kotlinTeam.storage.domain.repository

import com.example.kotlinTeam.storage.common.StorageStatuses
import com.example.kotlinTeam.storage.domain.model.StorageCategory
import com.example.kotlinTeam.storage.domain.model.StorageProduct
import kotlinx.coroutines.flow.Flow

interface StorageRepository {
    fun getCategory(): Flow<StorageStatuses<List<StorageCategory>>>

    fun getProduct(parentId: Int): Flow<StorageStatuses<List<StorageProduct>>>

    fun selectProduct(storageProduct: StorageProduct): Flow<StorageStatuses<StorageProduct>>
}
