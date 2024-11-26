package com.example.g10_todotasks.UI

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.g10_todotasks.DB.TaskDatabase
import com.example.g10_todotasks.Model.DatabaseProvider
import com.example.g10_todotasks.Model.Task
import com.example.g10_todotasks.R
import com.example.g10_todotasks.databinding.ActivityMainBinding
import kotlinx.coroutines.launch



class MainActivity : AppCompatActivity() {


    private lateinit var db: TaskDatabase
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: Adapter
    private lateinit var dataList: ArrayList<TaskDatabase>
    private lateinit var titleList: ArrayList<String>
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        db = DatabaseProvider.getDatabase(this)
        lifecycleScope.launch {
            db.taskDao().insert(
                Task(
                    title = "Comprar leche",
                    description = "Comprar leche en el supermercado",
                    isCompleted = false)
            )   }





        titleList = arrayListOf(
            "Fazer compras no supermercado",
            "task2",
            "task3",
            "task4",
            "task5"
        )


        dataList = arrayListOf()
        getData()


        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.setLayoutManager(
            LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false
            )
        )


        adapter = Adapter(dataList)
        recyclerView.setAdapter(adapter)
    }

    private fun initUI() {
        binding.customToolbarMain.arrBack.setOnClickListener {
            handleButtonClickToMain()
        }
        binding.customToolbarMain.addTask.setOnClickListener {
            handleButtonClickToDetail()
        }
        binding.customToolbarMain.mainTitle.text = "My tasks"
    }


    private fun handleButtonClickToDetail() {
        val intent = Intent(applicationContext, DetailActivity::class.java)
       startActivity(intent)
        finish()
    }

    private fun handleButtonClickToMain() {
        Toast.makeText(applicationContext, "You are already on the home", Toast.LENGTH_SHORT).show()
    }

    private fun getData() {
        for (title in titleList)
            dataList.add(Task(title = title, description = "description", isCompleted = false))

        }
    }

private fun <E> java.util.ArrayList<E>.add(task: Task) {
    TODO("Not yet implemented")
}
