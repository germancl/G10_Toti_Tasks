package com.example.g10_todotasks

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var dataList: ArrayList<Data>
    lateinit var titleList: ArrayList<String>
    lateinit var spinDate: ArrayList<String>
    lateinit var spinHour: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        titleList = arrayListOf(
            "Fazer compras no supermercado",
            "task2",
            "task3",
            "task4",
            "task5"
        )

        spinDate = arrayListOf(
            "01/01/2001",
            "01/01/2001",
            "01/01/2001",
            "01/01/2001",
            "01/01/2001"
        )

        spinHour = arrayListOf(
            "00:00",
            "01:00",
            "02:00",
            "03:00",
            "04:00",
            "05:00"
        )

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        dataList = arrayListOf<Data>()
        getData()

        val spinner: Spinner = findViewById(R.id.dates)
        val arrayAdapter =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, spinDate)

        spinner.adapter = arrayAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Toast.makeText(
                    applicationContext,
                    "selected date is = " + spinDate[position],
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        val spinnerHour: Spinner = findViewById(R.id.times)
        val arrayAdapterHour =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, spinHour)

        spinnerHour.adapter = arrayAdapterHour
        spinnerHour.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Toast.makeText(
                    applicationContext,
                    "selected hour is = " + spinHour[position],
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
    }

    private fun getData() {
        for (i in titleList.indices) {
            val dataClass = Data(titleList[i])
            dataList.add(dataClass)
        }
        recyclerView.adapter = Adapter(dataList)
    }
}