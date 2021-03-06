package com.example.todo.adapter

import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.data.Task
import com.example.todo.databinding.TaskCardBinding
import com.example.todo.fragments.ListItemsFragmentDirections
import com.example.todo.viewModel.TodoViewModel
import java.text.DateFormatSymbols


class TaskCardAdapter : RecyclerView.Adapter<TaskCardAdapter.MyTaskCardAdapter>() {

    var todoList = emptyList<Task>()
    private var viewModel: TodoViewModel? = null

    class MyTaskCardAdapter(val binding: TaskCardBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        if (viewModel == null) {
            viewModel =
                ViewModelProvider((recyclerView.context as ViewModelStoreOwner))[TodoViewModel::class.java]
        }
    }

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
        holder.binding.taskDescriptionInfo.text =
            if (todoList[position].toBuyIngredients == "") "" else todoList[position].toBuyIngredients

        holder.binding.listCardRow.setOnClickListener {
            holder.binding.root.findNavController()
                .navigate(ListItemsFragmentDirections.actionListItemsToUpdateFragment(todoList[position]))
        }

        val (dd, mm, yy) = Triple(
            todoList[position].dd,
            todoList[position].mm,
            todoList[position].yy
        )

        val dateFormat: String = "Created on ${dd} ${DateFormatSymbols().months[mm]}, ${yy}"
        holder.binding.taskDateInfo.text = dateFormat.toString()

        holder.binding.dropDownArrow.setOnClickListener {
            setVisibility(todoList[position], holder)
        }
    }

    override fun getItemCount() = todoList.size

    fun setTaskData(task: List<Task>) {
        val diffUtil = ItemsListDiffUtil(todoList, task)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        this.todoList = task
        diffResult.dispatchUpdatesTo(this)
    }

    private fun setVisibility(task: Task, holder: MyTaskCardAdapter) {
        if (holder.binding.taskDescriptionInfo.visibility == View.GONE) {
            TransitionManager.beginDelayedTransition(
                holder.binding.listCardRow,
                AutoTransition()
            )
            holder.binding.taskDescriptionInfo.visibility = View.VISIBLE
            holder.binding.taskDateInfo.visibility = View.VISIBLE
            holder.binding.dropDownArrow.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24)
            viewModel!!.updateTask(task)
        } else {
            TransitionManager.beginDelayedTransition(
                holder.binding.listCardRow,
                AutoTransition()
            )
            holder.binding.taskDescriptionInfo.visibility = View.GONE
            holder.binding.taskDateInfo.visibility = View.GONE

            holder.binding.dropDownArrow.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24)
            viewModel!!.updateTask(task)
        }
    }
}
