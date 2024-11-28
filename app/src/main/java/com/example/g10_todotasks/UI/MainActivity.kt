@file:Suppress("DEPRECATION")

package com.example.g10_todotasks.UI

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.g10_todotasks.Model.Data
import com.example.g10_todotasks.R
import com.example.g10_todotasks.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: Adapter
    private lateinit var dataList: ArrayList<Data>
    private lateinit var titleList: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Configurar ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializar la interfaz
        initUI()

        // Crear lista de títulos y datos
        titleList = arrayListOf(
            "Fazer compras no supermercado",
            "task2",
            "task3",
            "task4",
            "task5"
        )

        dataList = arrayListOf()
        getData()

        // Configurar RecyclerView
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = Adapter(dataList)
        binding.recyclerView.adapter = adapter
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
        val intent = Intent(applicationContext, DetailActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun getData() {
        for (i in titleList.indices) {
            dataList.add(Data("sampleData $i"))
        }
    }
}

