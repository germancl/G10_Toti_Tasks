package com.example.g10_todotasks.DB

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.g10_todotasks.Model.UserData

@Dao
interface UserDao {
    @Insert
    fun insertAll(vararg usersData: UserData)

    @Query("SELECT * FROM tasks_table")
    fun getAll(): List<UserData>

    @Update
    fun updateUser(user: UserData)

    @Delete
    fun delete(user: UserData)

//    @Query("SELECT * FROM tasks_table WHERE id IN (:tasksIds)")
//    fun loadAllByIds(tasksIds: IntArray): List<UserData>
//
//    @Query(
//        "SELECT * FROM tasks_table WHERE task_name LIKE :name"
//    )
//    fun findByName(name: String): UserData
}