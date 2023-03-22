package com.example.kotkin_team.storage.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.kotkin_team.storage.data.db.model.StorageProductDao

@Database(entities = [StorageProductEntity::class], version = 1)
abstract class StorageProductDatabase : RoomDatabase() {
    abstract fun userDao(): StorageProductDao
}