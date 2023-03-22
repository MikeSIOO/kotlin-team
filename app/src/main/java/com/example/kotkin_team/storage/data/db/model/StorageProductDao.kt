package com.example.kotkin_team.storage.data.db.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.kotkin_team.storage.data.db.StorageProductEntity

@Dao
interface StorageProductDao {
//    @Query("SELECT * FROM product WHERE id=:id")
//    fun getById(id: Int): StorageProductEntity
//
//    @Query("SELECT * FROM product WHERE name ILIKE %:name%")
//    fun getByName(name: String): List<StorageProductEntity>

    @Query("SELECT * FROM product WHERE parentId=:parentId")
    fun getByParent(parentId: Int): List<StorageProductEntity>

    @Insert
    fun insertAll(vararg storageProductEntity: StorageProductEntity)

    @Delete
    fun delete(storageProductEntity: StorageProductEntity)
}