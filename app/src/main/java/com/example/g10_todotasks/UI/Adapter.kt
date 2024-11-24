package com.example.g10_todotasks.UI

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.g10_todotasks.Model.Data
import com.example.g10_todotasks.R

class Adapter(private val dataList: ArrayList<Data>) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.tasks, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
//        val currentItem = dataList[position]
//        viewHolder.rvTitle.text = currentItem.dataTitle

        viewHolder.bind(dataList[position])
    }
}