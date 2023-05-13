package com.example.kotlinTeam.storage.domain.events

import com.example.kotlinTeam.storage.domain.model.StorageDataModel

sealed class StorageEvents {
    object InitCategory : StorageEvents()

    data class InitProduct(val parentId: String) : StorageEvents()

    object LoadProduct : StorageEvents()

    data class SelectProduct(val storageProduct: StorageDataModel.StorageProduct) : StorageEvents()
}
