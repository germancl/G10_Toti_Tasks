package com.example.g10_todotasks.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserData(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "user_name") val name: String,
    @ColumnInfo(name = "user_age") val age: Int,
//    TODO: ADD checkbox para concluir a tarefa
//    @ForeignKey
)