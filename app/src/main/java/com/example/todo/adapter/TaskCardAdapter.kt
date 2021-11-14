package com.example.todo.adapter

import android.transition.AutoTransition
import android.transition.TransitionManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.data.State
import com.example.todo.data.Task
import com.example.todo.databinding.TaskCardBinding
import com.example.todo.fragments.ListItemsFragmentDirections
import com.example.todo.priorityClasses.Priority
import java.text.DateFormatSymbols

class TaskCardAdapter : RecyclerView.Adapter<TaskCardAdapter.MyTaskCardAdapter>() {

    var todoList = emptyList<Task>()
//    var stateList = emptyList<State>()

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

        holder.binding.listCardRow.setOnLongClickListener {
            holder.binding.root.findNavController()
                .navigate(ListItemsFragmentDirections.actionListItemsToUpdateFragment(todoList[position]))
            true
        }

        val (dd, mm, yy) = Triple(
            todoList[position].dd,
            todoList[position].mm,
            todoList[position].yy
        )

        val dateFormat: String = "Created on ${dd} ${DateFormatSymbols().months[mm]}, ${yy}"
        holder.binding.taskDateInfo.text = dateFormat.toString()

        Log.w("HHHHHHHHHHH", "DFSDFSDFDSF")
        setState.setOnLoadVisibility(todoList[position].id, holder)

        holder.binding.listCardRow.setOnClickListener {
            setState.onTaskAddState(todoList[position].id, holder)
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
        val diffUtil = ItemsListDiffUtil(todoList, task)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        this.todoList = task
        diffResult.dispatchUpdatesTo(this)
    }
//
//    private fun ifPresent(task: Task): Boolean {
//        var result: Boolean = false
//        for (i in stateList) {
//            if (i.dataID == task.id) {
//                result = true
//            }
//        }
//        return result
//    }

    fun setVisibility(statePosition: State, holder: MyTaskCardAdapter) {
        if (statePosition.isExpanded == 1) {
            TransitionManager.beginDelayedTransition(
                holder.binding.listCardRow,
                AutoTransition()
            )
            holder.binding.taskDescriptionInfo.visibility = View.VISIBLE
            holder.binding.taskDateInfo.visibility = View.VISIBLE
        } else if (statePosition.isExpanded == 0) {
            TransitionManager.beginDelayedTransition(
                holder.binding.listCardRow,
                AutoTransition()
            )
            holder.binding.taskDescriptionInfo.visibility = View.GONE
            holder.binding.taskDateInfo.visibility = View.GONE
        }
    }

//    fun setStateData(state: List<State>) {
//        this.stateList = state
//    }

    lateinit var isExpandedSet: SetIsExpanded
    lateinit var setState: AddState

    interface AddState {
        fun onTaskAddState(id: Int, holder: MyTaskCardAdapter)
        fun setOnLoadVisibility(id: Int, holder: MyTaskCardAdapter)
    }

    fun setStateForAdd(listener: AddState) {
        this.setState = listener
    }

    interface SetIsExpanded {
        fun onItemClickSetExpanded(state: State, isExpand: Int)
    }

    fun onItemClickSetExpanded(listener: SetIsExpanded) {
        this.isExpandedSet = listener
    }
}
