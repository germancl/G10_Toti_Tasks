package com.example.g10_todotasks.Model

import android.content.Context
import androidx.room.Room
import com.example.g10_todotasks.DB.TaskDatabase

object DatabaseProvider {
    private var INSTANCE: TaskDatabase? = null

    fun getDatabase(context: Context): TaskDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                TaskDatabase::class.java,
                "task_database"
            ).build()
            INSTANCE = instance
            instance
        }
    }
}
