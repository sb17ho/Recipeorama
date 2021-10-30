package com.example.todo

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.databinding.TaskCardBinding

class TaskCardAdapter : RecyclerView.Adapter<TaskCardAdapter.MyTaskCardAdapter>() {

    class MyTaskCardAdapter(val binding: TaskCardBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyTaskCardAdapter {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: MyTaskCardAdapter, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

}