package com.example.g10_todotasks.UI

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.g10_todotasks.DB.AppDataBase
import com.example.g10_todotasks.Model.UserData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskViewModel : ViewModel() {

    private lateinit var database: AppDataBase

    val taskItems: MutableLiveData<List<UserData>> = MutableLiveData()

    fun initDb(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            database = AppDataBase.getDatabase(context)
            listTaskItem()
        }
    }

    private fun listTaskItem() {
        viewModelScope.launch(Dispatchers.IO) {
            val listItems = database.userDao().getAll()
            taskItems.postValue(listItems)
        }
    }

    fun addTask(user: UserData) {
        viewModelScope.launch(Dispatchers.IO) {
            database.userDao().insertAll(user)
            listTaskItem()
        }
    }

    fun updateTask(user: UserData) {
        viewModelScope.launch(Dispatchers.IO) {
            database.userDao().updateUser(user)
            listTaskItem()
        }
    }

    fun deleteTask(user: UserData) {
        viewModelScope.launch(Dispatchers.IO) {
            database.userDao().delete(user)
            listTaskItem()
        }
    }
}