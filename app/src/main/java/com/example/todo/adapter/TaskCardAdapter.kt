package com.example.todo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.data.Task
import com.example.todo.databinding.TaskCardBinding
import kotlin.properties.Delegates

class TaskCardAdapter : RecyclerView.Adapter<TaskCardAdapter.MyTaskCardAdapter>() {

    var todoList = emptyList<Task>()
    var position by Delegates.notNull<Int>()

    class MyTaskCardAdapter(val binding: TaskCardBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyTaskCardAdapter =
        MyTaskCardAdapter(
            TaskCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: MyTaskCardAdapter, position: Int) {
        this.position = position
        holder.binding.taskNameView.text = todoList[position].title
        /*TODO Handle priority label*/
    }

    override fun getItemCount() = todoList.size

    fun setTaskData(task: List<Task>) {
        this.todoList = task
        notifyItemChanged(position)
    }
}
