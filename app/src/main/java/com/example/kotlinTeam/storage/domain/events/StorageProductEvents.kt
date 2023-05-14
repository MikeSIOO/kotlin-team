package com.example.kotlinTeam.storage.domain.events

import com.example.kotlinTeam.storage.domain.model.StorageProduct

sealed class StorageProductEvents {
    data class InitProduct(val parentId: String) : StorageProductEvents()

    object LoadProduct : StorageProductEvents()

    data class SelectProduct(val storageProduct: StorageProduct) : StorageProductEvents()
}
