package com.frensky.porto.data.local.database

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

object Migration {
    // Don't forget to change the version in AppDatabase after you add MIGRATION
    val MIGRATION_1_2: Migration =
        object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
            }
        }

    /*
    val MIGRATION_2_3: Migration =
        object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Drop the old table
                database.execSQL("DROP TABLE IF EXISTS SampleEntity")

                // Create the new table
                database.execSQL(
                    "CREATE TABLE SampleEntity (" +
                            "primary_id TEXT PRIMARY KEY NOT NULL, " +
                            "param1 TEXT, " +
                            "param2 TEXT, " +
                            "param3 INTEGER)"
                )
            }
        }*/
}