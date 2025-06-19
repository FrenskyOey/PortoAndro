package com.frensky.porto.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.frensky.porto.data.local.database.entity.SampleEntity

@Dao
interface SampleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveData(data: SampleEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveBatchData(data: List<SampleEntity>)

    @Query("SELECT * FROM SampleEntity where `primary_id` = 0 ORDER BY primary_id ASC")
    fun getSampleData(): List<SampleEntity>

    @Query("delete from SampleEntity")
    fun deleteAll()
}
