package com.example.testapp.database

import android.content.Context
import androidx.room.Room
import com.example.testapp.database.entity.BootTime
import java.util.*

class DBManager(context: Context) {
    private var database: AppDatabase

    init {
        database = Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
            .build()
    }

    fun getDB(): AppDatabase {
        return database
    }

    fun closeDB() {
        database.close()
    }

    fun saveLastBootTime(time: Long) {
        database.bootTimeDao().insert(BootTime(time))
    }

    fun getLastBootTime(): List<BootTime> {
        return database.bootTimeDao().getAll()
    }

    companion object {
        const val DB_NAME = "test_database_name"
    }
}