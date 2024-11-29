package com.example.g10_todotasks.UI

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.g10_todotasks.Model.TaskItem
import com.example.g10_todotasks.R


class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(task: TaskItem) {
        itemView.findViewById<TextView>(R.id.tasksCheckBox).text = task.dataTitle
    }
}