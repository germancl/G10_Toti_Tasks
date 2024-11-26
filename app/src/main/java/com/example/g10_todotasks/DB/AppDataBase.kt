package com.example.g10_todotasks.DB

import android.service.autofill.UserData
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserData::class], version = 1, exportSchema = false)
abstract class TaskDatabase(title: String, description: String, isCompleted: Boolean) : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}




















