package com.example.g10_todotasks.UI

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import com.example.g10_todotasks.R
import com.example.g10_todotasks.databinding.ActivityDetailBinding
import java.text.SimpleDateFormat
import java.util.Locale

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    private lateinit var dateSelected: TextView
    private lateinit var btnPickDate: ImageView
    private var calendar = Calendar.getInstance()
    private lateinit var btnPickTime: ImageView
    private lateinit var timeSelected: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        binding = setContentView(this, R.layout.activity_detail)

        initUI()

        dateSelected = findViewById(R.id.writeDelDate)
        btnPickDate = findViewById(R.id.calendar_btn)

        btnPickDate.setOnClickListener {
            showDatePicker()
        }

        timeSelected = findViewById(R.id.writeDelTime)
        btnPickTime = findViewById(R.id.clock_btn)

        btnPickTime.setOnClickListener {
            showTimePicker()
        }
    }

    private fun initUI() {
        binding.customToolbarDetails.arrBack.setOnClickListener {
            handleButtonClickToMain()
        }
        binding.customToolbarDetails.detailsTitle.text = "My tasks details"
    }

    private fun handleButtonClickToMain() {
        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun showDatePicker() {
        val datePickerDialog = DatePickerDialog(
            this, { DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                val selectDate = Calendar.getInstance()
                selectDate.set(year, monthOfYear, dayOfMonth)
                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                val formattedDate = dateFormat.format(selectDate.time)
                dateSelected.text = formattedDate
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }

    private fun showTimePicker() {
        val cal = Calendar.getInstance()
        val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
            cal.set(Calendar.HOUR_OF_DAY, hour)
            cal.set(Calendar.MINUTE, minute)
            timeSelected.text = SimpleDateFormat("HH:mm").format(cal.time)
        }
        TimePickerDialog(
            this,
            timeSetListener,
            cal.get(Calendar.HOUR_OF_DAY),
            cal.get(Calendar.MINUTE),
            true
        ).show()
    }
}