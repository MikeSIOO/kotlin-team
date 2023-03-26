package com.example.kotkin_team.storage.data.db.service

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.kotkin_team.storage.data.db.model.StorageProductEntity

@Dao
interface StorageProductDao {
//    @Query("SELECT * FROM StorageProductEntity WHERE id=:id")
//    suspend fun getById(id: Int): StorageProductEntity
//
//    @Query("SELECT * FROM StorageProductEntity WHERE name ILIKE %:name%")
//    suspend fun getByName(name: String): List<StorageProductEntity>

    @Query("SELECT * FROM StorageProductEntity WHERE parentId=:parentId")
    suspend fun getByParent(parentId: Int): List<StorageProductEntity>

    @Insert
    suspend fun insert(storageProductEntity: StorageProductEntity)

    @Delete
    suspend fun delete(storageProductEntity: StorageProductEntity)
}