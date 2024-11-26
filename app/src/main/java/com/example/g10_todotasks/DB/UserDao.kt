package com.example.g10_todotasks.DB

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Dao
import androidx.room.Update
import com.example.g10_todotasks.Model.Task

@Dao interface TaskDao {
    @Insert
    suspend fun insert(task: Task)

    @Delete
    suspend fun delete(task: Task)

    @Update
    suspend fun update(task: Task)

    @Query("SELECT * FROM task_table")
    suspend fun getAllTasks(): List<Task>

    @Query("DELETE FROM task_table WHERE id = :taskId")
    suspend fun deleteTask(taskId: Int) }
