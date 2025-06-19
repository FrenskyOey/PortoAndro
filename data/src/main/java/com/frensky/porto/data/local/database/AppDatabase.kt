package com.frensky.porto.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.frensky.porto.data.local.database.dao.SampleDao
import com.frensky.porto.data.local.database.entity.SampleEntity

@Database(
    entities = [
        SampleEntity::class
    ],
    version = 2,
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun sampleDao(): SampleDao

    suspend fun clearTables() {
        sampleDao().deleteAll()
    }
}