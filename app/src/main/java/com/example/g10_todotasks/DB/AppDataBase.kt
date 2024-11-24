package com.example.g10_todotasks.DB

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.g10_todotasks.Model.UserData

@Database(
    entities = [
        UserData::class
    ], version = 1
)

abstract class AppDataBase : RoomDatabase() {
    //TODO:
}