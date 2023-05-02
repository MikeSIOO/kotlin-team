package com.example.kotlinTeam.storage.domain.model

// модель данных
data class StorageProduct(
    val id: Int,
    val name: String,
    val image: String?,
    val parentId: Int,
    var selected: Boolean
) {
    fun select() {
        selected = !selected
    }
}
