package com.example.g10_todotasks.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks_table")
data class TaskData(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "task_name") val name: String,
    @ColumnInfo(name = "task_date") val date: String?,
    @ColumnInfo(name = "task_time") val time: String?
//    @ColumnInfo(name = "user_age") val age: Int,
//    TODO: ADD checkbox para concluir a tarefa
//    @ForeignKey
)