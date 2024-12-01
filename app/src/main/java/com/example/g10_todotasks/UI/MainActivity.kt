package com.example.g10_todotasks.UI

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.g10_todotasks.Model.TaskData
import com.example.g10_todotasks.Model.TaskItem
import com.example.g10_todotasks.R
import com.example.g10_todotasks.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), Adapter.Listener {
    private val viewModel: MainViewModel by viewModels()

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: Adapter
    private lateinit var taskItemList: ArrayList<TaskItem>
    private lateinit var titleList: ArrayList<String>
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setupObservers()

        initUI()

        viewModel.initDb(applicationContext)

        adapter = Adapter(mutableListOf(), this)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter

        binding.customToolbarMain.addTaskBtn.setOnClickListener {
            openUserDialog()
        }
    }

    private fun openUserDialog(task: TaskData? = null) {
        val dialog = DetailActivity.newInstance(task, object : DetailActivity.Listener {
            override fun onAddUser(task: TaskData) {
                viewModel.addTask(task)
            }

            override fun onEditUser(task: TaskData) {
                viewModel.updateTask(task)
            }
        })

        dialog.show(supportFragmentManager, "AddUserDialog")
    }

    private fun setupObservers() {
        viewModel.taskItems.observe(this) { taskItems ->
            adapter.addUsers(taskItems)
        }
    }

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

    override fun onClick(task: TaskData) {
        openUserDialog(task)
    }

    override fun onLongClick(task: TaskData) {
        viewModel.deleteTask(task)
    }
}