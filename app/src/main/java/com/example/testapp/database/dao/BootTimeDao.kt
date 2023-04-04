package com.example.testapp.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.testapp.database.entity.BootTime


@Dao
interface BootTimeDao {
    @Query("SELECT * FROM boot_times")
    fun getAll(): List<BootTime>

    //    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
//    fun loadAllByIds(userIds: IntArray): List<User>
//
//    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
//            "last_name LIKE :last LIMIT 1")
//    fun findByName(first: String, last: String): User
    @Insert
    fun insert(bootTime: BootTime)

    @Delete
    fun delete(user: BootTime)
}