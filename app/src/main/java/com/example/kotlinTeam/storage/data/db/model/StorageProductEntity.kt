package com.example.kotlinTeam.storage.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class StorageProductEntity(
    @PrimaryKey val id: String = "",
    @ColumnInfo(name = "title") val title: String? = null,
    @ColumnInfo(name = "parentId") val parentId: String? = null
)
