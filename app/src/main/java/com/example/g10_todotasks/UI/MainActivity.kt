package com.example.g10_todotasks.UI

import android.content.Intent
import android.graphics.Canvas
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.g10_todotasks.Model.TaskData
import com.example.g10_todotasks.R
import com.example.g10_todotasks.databinding.ActivityMainBinding
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator

class MainActivity : AppCompatActivity(), Adapter.Listener {
    private val viewModel: MainViewModel by viewModels()

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: Adapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initUI()

        setupObservers()

        viewModel.initDb(applicationContext)

        adapter = Adapter(mutableListOf(), this)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        recyclerView.adapter = adapter

        binding.customToolbarMain.addTaskBtn.setOnClickListener {
            openUserDialog()
        }

        ItemTouchHelper(object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false

            }

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                RecyclerViewSwipeDecorator.Builder(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
                    .addSwipeRightBackgroundColor(com.google.android.material.R.color.design_default_color_error)
                    .addSwipeRightActionIcon(R.drawable.delete_icon)
                    .addSwipeLeftBackgroundColor(com.google.android.material.R.color.design_default_color_error)
                    .addSwipeLeftActionIcon(R.drawable.delete_icon)
                    .create()
                    .decorate()

                super.onChildDraw(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                viewModel.deleteTask(adapter.getTaskAt(viewHolder.bindingAdapterPosition))
            }
        }).attachToRecyclerView(recyclerView)
    }

    private fun setupObservers() {
        viewModel.taskItems.observe(this) { taskItems ->
            adapter.addUsers(taskItems)
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

    private fun initUI() {
        binding.customToolbarMain.arrBackBtn.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
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

    override fun onClick(task: TaskData) {
        Toast.makeText(applicationContext, getString(R.string.guideClick), Toast.LENGTH_SHORT)
            .show()
    }

    override fun onLongClick(task: TaskData) {
        openUserDialog(task)
    }
}