package com.example.kotlinTeam.storage.domain.events

import com.example.kotlinTeam.storage.domain.model.StorageProduct

sealed class StorageProductEvents {
    data class InitProduct(val parentId: Int) : StorageProductEvents()

    object LoadProduct : StorageProductEvents()

    data class SelectProduct(val storageProduct: StorageProduct) : StorageProductEvents()
}
