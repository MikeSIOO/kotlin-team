package com.example.kotkin_team.storage.domain.events

sealed class StorageProductEvents {
    data class LoadProduct(val parentId: Int) : StorageProductEvents()
}