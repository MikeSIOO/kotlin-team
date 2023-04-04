package com.example.kotkin_team.storage.data.db.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.kotkin_team.storage.data.db.model.StorageProductEntity
import com.example.kotkin_team.storage.data.db.service.StorageProductDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@Database(entities = [StorageProductEntity::class], version = 1)
abstract class StorageProductDatabase : RoomDatabase() {
    abstract val storageProductDao: StorageProductDao
}
