package com.example.kotlinTeam.storage.domain.repository

import com.example.kotlinTeam.storage.common.StorageStatuses
import com.example.kotlinTeam.storage.domain.model.StorageDataModel
import kotlinx.coroutines.flow.Flow

interface StorageRepository {
    fun selectProduct(storageProduct: StorageDataModel.StorageProduct): Flow<StorageStatuses<StorageDataModel.StorageProduct>>

    fun getSelectedProducts(): Flow<StorageStatuses<List<StorageDataModel.StorageProduct>>>
}
