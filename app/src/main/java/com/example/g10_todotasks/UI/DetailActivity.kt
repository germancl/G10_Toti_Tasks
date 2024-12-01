package com.example.g10_todotasks.UI

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.g10_todotasks.Model.TaskData
import com.example.g10_todotasks.R
import com.example.g10_todotasks.databinding.ActivityDetailBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.util.Locale

class DetailActivity : DialogFragment() {
    private lateinit var editTextName: TextInputEditText
    private var taskData: TaskData? = null
    private var toEdit: Boolean = false
    private var listener: Listener? = null

    private lateinit var binding: ActivityDetailBinding

    private lateinit var dateSelected: TextView
    private lateinit var btnPickDate: ImageView
    private var calendar = Calendar.getInstance()
    private lateinit var btnPickTime: ImageView
    private lateinit var timeSelected: TextView

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.activity_detail, null)
//        binding = setContentView(context, R.layout.activity_detail)/

        editTextName = view.findViewById(R.id.task_name)

        if (taskData != null) {
            editTextName.setText(taskData?.name)
        }

//        initUI()

//        dateSelected = view.findViewById(R.id.writeDelDate)
//        btnPickDate = view.findViewById(R.id.calendar_btn)
//
//        btnPickDate.setOnClickListener {
//            showDatePicker()
//        }
//
//        timeSelected = view.findViewById(R.id.writeDelTime)
//        btnPickTime = view.findViewById(R.id.clock_btn)
//
//        btnPickTime.setOnClickListener {
//            showTimePicker()
//        }

        return MaterialAlertDialogBuilder(requireActivity(), theme)
            .setView(view)
//            .setTitle("Adicionar Usuário")
//            .setMessage("Adicione aqui")
            .setPositiveButton("OK") { dialog, _ ->
                val user = TaskData(
                    taskData?.id ?: 0,
                    editTextName.text.toString(),
//                    editTextAge.text.toString().toIntOrNull() ?: 0
                )

                if (toEdit)
                    listener?.onEditUser(user)
                else
                    listener?.onAddUser(user)

                dialog.dismiss()
            }
            .setNegativeButton(R.string.cancel) { dialog, _ ->
                dialog.cancel()
            }
            .create()

    }

    interface Listener {
        fun onAddUser(task: TaskData)
        fun onEditUser(task: TaskData)
    }

    companion object {
        fun newInstance(task: TaskData?, listener: Listener): DetailActivity =
            DetailActivity().apply {
                if (task != null) {
                    this.taskData = task
                    toEdit = true
                }
                this.listener = listener
            }
    }

    private fun initUI() {
        binding.customToolbarDetails.arrBackBtn.setOnClickListener {
            handleButtonClickToMain()
        }
        binding.customToolbarDetails.detailsTitle.text = getString(R.string.detailsTitle)
    }

    private fun handleButtonClickToMain() {
        val intent = Intent(context, MainActivity::class.java)
        startActivity(intent)
//        finish()
    }

    private fun showDatePicker() {
        val datePickerDialog = context?.let {
            DatePickerDialog(
                it, { DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int ->
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
        }
        datePickerDialog?.show()
    }

    private fun showTimePicker() {
        val cal = Calendar.getInstance()
        val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
            cal.set(Calendar.HOUR_OF_DAY, hour)
            cal.set(Calendar.MINUTE, minute)
            timeSelected.text = SimpleDateFormat("HH:mm").format(cal.time)
        }
        TimePickerDialog(
            context,
            timeSetListener,
            cal.get(Calendar.HOUR_OF_DAY),
            cal.get(Calendar.MINUTE),
            true
        ).show()
    }
}