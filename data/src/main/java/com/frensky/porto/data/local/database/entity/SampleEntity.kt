package com.frensky.porto.data.local.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class SampleEntity {
    @PrimaryKey
    @ColumnInfo(name = "primary_id")
    var id: String = ""

    @ColumnInfo(name = "param1")
    var param1: String = ""

    @ColumnInfo(name = "param2")
    var param2: String = ""
}