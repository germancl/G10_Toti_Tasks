package com.example.g10_todotasks.DB

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.g10_todotasks.Model.TaskData

@Dao
interface UserDao {
    @Insert
    fun insertAll(vararg usersData: TaskData)

    @Query("SELECT * FROM tasks_table")
    fun getAll(): List<TaskData>

    @Update
    fun updateUser(task: TaskData)

    @Delete
    fun delete(task: TaskData)

//    @Query("SELECT * FROM tasks_table WHERE id IN (:tasksIds)")
//    fun loadAllByIds(tasksIds: IntArray): List<UserData>
//
//    @Query(
//        "SELECT * FROM tasks_table WHERE task_name LIKE :name"
//    )
//    fun findByName(name: String): UserData
}