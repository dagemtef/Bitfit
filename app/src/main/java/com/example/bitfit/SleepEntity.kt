package com.example.bitfit

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sleep_table")
class SleepEntity (
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "dayOfWeek") val day: String?,
    @ColumnInfo(name = "sleepTime") val sleepTime: String?,
    @ColumnInfo(name = "wakeTime") val wakeTime: String?,
)