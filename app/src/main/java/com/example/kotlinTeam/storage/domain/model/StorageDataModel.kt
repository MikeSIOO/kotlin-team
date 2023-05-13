package com.example.kotlinTeam.storage.domain.model

sealed class StorageDataModel {
    data class StorageCategory(
        val id: String?,
        val title: String?,
        val image: String?,
    ) : StorageDataModel()
    data class StorageProduct(
        val id: String?,
        val title: String?,
        val image: String?,
        val parentId: String?,
        var selected: Boolean
    ) : StorageDataModel() {
        fun select() {
            selected = !selected
        }
    }
}

