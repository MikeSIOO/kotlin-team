package com.example.kotkin_team.storage.domain.events

import com.example.kotkin_team.storage.domain.model.StorageProduct

sealed class StorageProductEvents {
    data class LoadProduct(val parentId: Int) : StorageProductEvents()

    data class SelectProduct(val storageProduct: StorageProduct) : StorageProductEvents()
}
