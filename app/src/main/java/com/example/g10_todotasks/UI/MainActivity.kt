package com.example.g10_todotasks.UI

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.g10_todotasks.Model.Data
import com.example.g10_todotasks.R
import com.example.g10_todotasks.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: Adapter
    private lateinit var dataList: ArrayList<Data>
    lateinit var titleList: ArrayList<String>

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initUI()

        titleList = arrayListOf(
            "Fazer compras no supermercado",
            "task2",
            "task3",
            "task4",
            "task5"
        )

        //        Init data
        dataList = arrayListOf<Data>()
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
        for (i in titleList.indices) {
            dataList.add(Data("sampleData"))
        }
    }
}