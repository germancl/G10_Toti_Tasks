package com.example.g10_todotasks.UI

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.g10_todotasks.DB.AppDataBase
import com.example.g10_todotasks.Model.TaskData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private lateinit var database: AppDataBase

    val taskItems: MutableLiveData<List<TaskData>> = MutableLiveData()

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

    fun addTask(task: TaskData) {
        viewModelScope.launch(Dispatchers.IO) {
            database.userDao().insertAll(task)
            listTaskItem()
        }
    }

    fun updateTask(task: TaskData) {
        viewModelScope.launch(Dispatchers.IO) {
            database.userDao().updateUser(task)
            listTaskItem()
        }
    }

    fun deleteTask(task: TaskData) {
        viewModelScope.launch(Dispatchers.IO) {
            database.userDao().delete(task)
            listTaskItem()
        }
    }

    fun deleteAllTasks() {
        viewModelScope.launch(Dispatchers.IO) {
            database.userDao().deleteAllTasks()
            listTaskItem()
        }
    }
}