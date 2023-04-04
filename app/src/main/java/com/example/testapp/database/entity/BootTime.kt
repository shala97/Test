package com.example.testapp.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "boot_times")
data class BootTime(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "time") val time: Long?
) {
    constructor(time: Long) : this(0, time)
}