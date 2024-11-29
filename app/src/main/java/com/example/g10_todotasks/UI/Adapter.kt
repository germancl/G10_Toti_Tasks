package com.example.g10_todotasks.UI

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.g10_todotasks.Model.TaskItem
import com.example.g10_todotasks.R

class Adapter(private val taskItemList: List<TaskItem>) :
    RecyclerView.Adapter<TaskViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): TaskViewHolder {
        val itemView =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.tasks, viewGroup, false)

        return TaskViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return taskItemList.size
    }

    override fun onBindViewHolder(viewHolder: TaskViewHolder, position: Int) {
        viewHolder.itemView.setOnClickListener {
            viewHolder.bind(taskItemList[position])
        }
    }
}

class TaskViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    fun bind(task: TaskItem) {
        view.findViewById<TextView>(R.id.writeTask).text = task.dataTitle
    }
}