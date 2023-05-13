package com.example.kotlinTeam.storage.domain.model

// модель данных
data class StorageProduct(
    val id: String?,
    val title: String?,
    val image: String?,
    val parentId: String?,
    var selected: Boolean
) {
    fun select() {
        selected = !selected
    }
}
