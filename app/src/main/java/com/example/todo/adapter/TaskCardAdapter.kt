package com.example.todo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.data.Task
import com.example.todo.databinding.TaskCardBinding
import com.example.todo.fragments.ListItemsFragmentDirections
import com.example.todo.priorityClasses.Priority

class TaskCardAdapter : RecyclerView.Adapter<TaskCardAdapter.MyTaskCardAdapter>() {

    var todoList = emptyList<Task>()

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
        holder.binding.taskNameView.text = todoList[position].title.replaceFirstChar {
            todoList[position].title.first().uppercase()
        }
        holder.binding.taskDescriptionInfo.text = todoList[position].description
        holder.binding.listCardRow.setOnClickListener {
            holder.binding.root.findNavController()
                .navigate(ListItemsFragmentDirections.actionListItemsToUpdateFragment(todoList[position]))
        }

        when (todoList[position].priority) {
            Priority.LOW -> {
                holder.binding.priorityLabel.setCardBackgroundColor(
                    ContextCompat.getColor(
                        holder.binding.root.context,
                        R.color.green
                    )
                )
            }
            Priority.MEDIUM -> holder.binding.priorityLabel.setCardBackgroundColor(
                ContextCompat.getColor(
                    holder.binding.root.context,
                    R.color.orange
                )
            )
            Priority.HIGH -> holder.binding.priorityLabel.setCardBackgroundColor(
                ContextCompat.getColor(
                    holder.binding.root.context,
                    R.color.red
                )
            )
            else -> holder.binding.priorityLabel.setCardBackgroundColor(
                ContextCompat.getColor(
                    holder.binding.root.context,
                    R.color.material_on_surface_stroke
                )
            )
        }
    }

    override fun getItemCount() = todoList.size

    fun setTaskData(task: List<Task>) {
        this.todoList = task
        notifyDataSetChanged()
    }
}
