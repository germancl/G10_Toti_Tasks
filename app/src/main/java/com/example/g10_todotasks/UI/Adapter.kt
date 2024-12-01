package com.example.g10_todotasks.UI

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.g10_todotasks.Model.TaskData
import com.example.g10_todotasks.R

class Adapter(
    private val taskItemList: MutableList<TaskData>,
    val listener: Listener
) : RecyclerView.Adapter<TaskViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): TaskViewHolder {
        val itemView =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.tasks, viewGroup, false)

        return TaskViewHolder(itemView)
    }

    override fun getItemCount() = taskItemList.size

    override fun onBindViewHolder(viewHolder: TaskViewHolder, position: Int) {
        viewHolder.itemView.setOnClickListener {
            listener.onClick(taskItemList[position])
        }

        viewHolder.itemView.setOnLongClickListener {
            listener.onLongClick(taskItemList[position])
            true
        }

//        TODO:onswipe


        viewHolder.bind(taskItemList[position])
    }

    fun addUsers(tasks: List<TaskData>) {
        val lastIndex = taskItemList.lastIndex
        notifyItemRangeRemoved(0, taskItemList.size)

        taskItemList.clear()
        taskItemList.addAll(tasks)

        notifyItemRangeInserted(lastIndex, taskItemList.size)
    }

    fun deleteItem(position: Int): TaskData {
        return taskItemList.removeAt(position)
    }

    fun getTaskAt(position: Int): TaskData {
        return taskItemList[position]
    }

    interface Listener {
        fun onClick(task: TaskData)
        fun onLongClick(task: TaskData)
        fun onSwipe(task: TaskData)
    }
}

class TaskViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    fun bind(task: TaskData) {
        view.findViewById<TextView>(R.id.tasksTitle).text =
            String.format(view.context.getString(R.string.taskName), task.name)
    }
}