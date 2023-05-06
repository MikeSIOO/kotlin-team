package com.example.kotlinTeam.storage.data.db.service

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.kotlinTeam.storage.data.db.model.StorageProductEntity

@Dao
interface StorageProductDao {
    @Query("SELECT * FROM StorageProductEntity WHERE parentId=:parentId")
    suspend fun getByParent(parentId: String): List<StorageProductEntity>

    @Query("SELECT * FROM StorageProductEntity WHERE id=:id")
    suspend fun getById(id: String): StorageProductEntity

    @Insert
    suspend fun insert(storageProductEntity: StorageProductEntity)

    @Delete
    suspend fun delete(storageProductEntity: StorageProductEntity)
}
