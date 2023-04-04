package com.example.testapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.testapp.database.dao.BootTimeDao
import com.example.testapp.database.entity.BootTime


@Database(entities = [BootTime::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bootTimeDao(): BootTimeDao
}