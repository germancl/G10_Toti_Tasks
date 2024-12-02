package com.example.g10_todotasks.UI

import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import com.example.g10_todotasks.R
class TaskAdapter(
    private val taskList: MutableList<Task> // Cambiado a MutableList
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val checkBox: CheckBox = itemView.findViewById(R.id.tasksCheckBox)
        val textView: TextView = itemView.findViewById(R.id.tasksTextView)
        val editButton: ImageView = itemView.findViewById(R.id.editButton)
        val deleteButton: ImageView = itemView.findViewById(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.tasks, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = taskList[position]

        holder.textView.text = task.description
        holder.checkBox.isChecked = task.isCompleted

        // Configurar el estilo tachado
        holder.textView.paintFlags = if (task.isCompleted) {
            holder.textView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            holder.textView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }

        // Configurar el CheckBox
        holder.checkBox.setOnCheckedChangeListener(null)
        holder.checkBox.setOnCheckedChangeListener { _, isChecked ->
            try {
                task.isCompleted = isChecked
                holder.textView.paintFlags = if (isChecked) {
                    holder.textView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                } else {
                    holder.textView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
                }
                if (isChecked) {
                    Toast.makeText(
                        holder.itemView.context,
                        "Tarea completada: ${task.description}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                notifyItemChanged(position)
            } catch (e: Exception) {
                Log.e("TaskAdapter", "Error: ${e.message}")
            }
        }

        // Configurar botón Editar
        holder.editButton.setOnClickListener {
            val editText = EditText(holder.itemView.context)
            editText.setText(task.description)

            val dialog = AlertDialog.Builder(holder.itemView.context)
                .setTitle("Editar Tarea")
                .setView(editText)
                .setPositiveButton("Guardar") { _, _ ->
                    task.description = editText.text.toString()
                    notifyItemChanged(position)
                    Toast.makeText(holder.itemView.context, "Tarea actualizada", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("Cancelar", null)
                .create()
            dialog.show()
        }

        // Configurar botón Eliminar
        holder.deleteButton.setOnClickListener {
            taskList.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, taskList.size)
            Toast.makeText(holder.itemView.context, "Tarea eliminada", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int = taskList.size
}
