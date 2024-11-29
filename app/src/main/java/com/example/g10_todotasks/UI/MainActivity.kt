package com.example.g10_todotasks.UI

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.g10_todotasks.Model.TaskItem
import com.example.g10_todotasks.R
import com.example.g10_todotasks.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val viewModel: TaskViewModel by viewModels()

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: Adapter
    private lateinit var taskItemList: ArrayList<TaskItem>
    private lateinit var titleList: ArrayList<String>
    private lateinit var binding: ActivityMainBinding
    private lateinit var taskViewModel: TaskViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewModel.initDb(applicationContext)

//        setRecyclerView()

        initUI()

        titleList = arrayListOf(
            "Fazer compras no supermercado",
            "task2",
            "task3",
            "task4",
            "task5"
        )

        //        Init data
        taskItemList = arrayListOf<TaskItem>()
        getData()

        //Set up RecyclerView
        recyclerView = findViewById(R.id.recyclerView)

        //Use a LinearLayoutManager
        //vertical
        recyclerView.setLayoutManager(
            LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false
            )
        )

        //Specify the adapter
        adapter = Adapter(taskItemList)
        recyclerView.setAdapter(adapter)
    }

//    private fun setRecyclerView() {
//        val mainActivity = this
//        taskViewModel.taskItems.observe(this) {
//            binding.recyclerView.apply {
//                {
//                    layoutManager = LinearLayoutManager(applicationContext)
//                }
//            }
//        }
//    }

    private fun initUI() {
        binding.customToolbarMain.arrBackBtn.setOnClickListener {
            handleButtonClickToMain()
        }
        binding.customToolbarMain.addTaskBtn.setOnClickListener {
            handleButtonClickToDetail()
        }
        binding.customToolbarMain.mainTitle.text = getString(R.string.mainActTitle)
    }


    private fun handleButtonClickToDetail() {
        val intent = Intent(applicationContext, DetailActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun handleButtonClickToMain() {
        Toast.makeText(applicationContext, getString(R.string.noMoreRet), Toast.LENGTH_SHORT).show()
    }

    private fun getData() {
        for (i in titleList.indices) {
            taskItemList.add(TaskItem("sampleData", null, null)) //TODO:
        }
    }

//    override fun onClick(user: UserData) {
//        openUserDialog(user)
//    }
//
//    override fun onLongClick(user: UserData) {
//        viewModel.deleteTask(user)
//    }
}