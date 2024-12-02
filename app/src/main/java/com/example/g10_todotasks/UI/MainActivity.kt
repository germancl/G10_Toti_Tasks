@file:Suppress("DEPRECATION")

package com.example.g10_todotasks.UI

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.g10_todotasks.Model.Data
import com.example.g10_todotasks.R
import com.example.g10_todotasks.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var taskAdapter: TaskAdapter
    private val taskList = mutableListOf<Task>()

    companion object {
        const val REQUEST_CODE_ADD_TASK = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Configurar ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializar RecyclerView con algunas tareas
        initTaskList()
        initRecyclerView()

        // Configurar la interfaz
        initUI()
    }

    private fun initTaskList() {
        taskList.addAll(
            listOf(
                Task(1, "Lavar os pratos", false),
                Task(2, "Fazer exercicio", false),
                Task(3, "Dar banho no cachorro", true)
            )
        )
    }

    private fun initRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        taskAdapter = TaskAdapter(taskList)
        binding.recyclerView.adapter = taskAdapter
    }

    private fun initUI() {
        // Configurar botón "arrBack"
        binding.customToolbarMain.arrBack.setOnClickListener {
            onBackPressed() // Regresar a la Activity anterior
        }

        // Configurar botón "addTask"
        binding.customToolbarMain.addTask.setOnClickListener {
            handleButtonClickToDetail()
        }

        // Configurar título
        binding.customToolbarMain.mainTitle.text = "My tasks"
    }

    private fun handleButtonClickToDetail() {
        val intent = Intent(this, DetailActivity::class.java)
        startActivityForResult(intent, REQUEST_CODE_ADD_TASK)
    }

    // Manejar el resultado devuelto desde DetailActivity
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_ADD_TASK && resultCode == Activity.RESULT_OK) {
            val description = data?.getStringExtra("TASK_DESCRIPTION") ?: ""
            val date = data?.getStringExtra("TASK_DATE") ?: ""
            val time = data?.getStringExtra("TASK_TIME") ?: ""

            // Agregar nueva tarea a la lista y actualizar RecyclerView
            if (description.isNotEmpty()) {
                val newTask = Task(taskList.size + 1, description, false)
                taskList.add(newTask)
                taskAdapter.notifyItemInserted(taskList.size - 1)
            }
        }
    }
}
