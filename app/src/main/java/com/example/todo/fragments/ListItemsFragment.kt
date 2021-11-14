package com.example.todo.fragments

import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.adapter.TaskCardAdapter
import com.example.todo.data.State
import com.example.todo.data.Task
import com.example.todo.databinding.FragmentListItemsBinding
import com.example.todo.viewModel.TodoViewModel
import com.google.android.material.snackbar.Snackbar

class ListItemsFragment : Fragment() {
    private lateinit var listItemsBinding: FragmentListItemsBinding
    private val viewModel: TodoViewModel by lazy { ViewModelProvider(this)[TodoViewModel::class.java] }
    private val recyclerAdapter by lazy { TaskCardAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        listItemsBinding = FragmentListItemsBinding.inflate(inflater, container, false)

        setHasOptionsMenu(true) //Called to make menu item

        apply_binding_listeners()
        applySwipeGesture()

        // Inflate the layout for this fragment
        return listItemsBinding.root
    }

    //Swipe to delete
    private fun applySwipeGesture() {
        ItemTouchHelper(object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ) = false

            // LEFT: DELETE AND RIGHT: ARCHIVE
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                when (direction) {
                    ItemTouchHelper.LEFT -> {
                        val removedTask: Task = recyclerAdapter.todoList[viewHolder.adapterPosition]
                        updateTask(recyclerAdapter.todoList[viewHolder.adapterPosition], 0, 1)
                        Snackbar.make(
                            requireActivity().findViewById(android.R.id.content),
                            "${removedTask.title} Deleted",
                            Snackbar.LENGTH_LONG
                        ).setAction("Undo") {
                            updateTask(removedTask, 0, 0)
                            viewModel.addTask(removedTask)
                        }.setAnchorView(requireActivity().findViewById(R.id.bottom_nav_bar)).show()
                    }
                    ItemTouchHelper.RIGHT -> {
                        val archiveTask: Task = recyclerAdapter.todoList[viewHolder.adapterPosition]
                        updateTask(recyclerAdapter.todoList[viewHolder.adapterPosition], 1, 0)
                        Snackbar.make(
                            requireActivity().findViewById(android.R.id.content),
                            "${archiveTask.title} Archived",
                            Snackbar.LENGTH_LONG
                        ).setAction("Undo") {
                            updateTask(archiveTask, 0, 0)
                            viewModel.addTask(archiveTask)
                        }.setAnchorView(requireActivity().findViewById(R.id.bottom_nav_bar)).show()
                    }
                }
            }
        }).attachToRecyclerView(listItemsBinding.listItem)
    }

    private fun updateTask(task: Task, isArchived: Int, isTrash: Int) {
        task.isArchived = isArchived
        task.isTrash = isTrash
        viewModel.updateTask(task)
    }

    private fun apply_binding_listeners() {

        listItemsBinding.apply {
            listItem.layoutManager = LinearLayoutManager(requireContext())
            listItem.adapter = recyclerAdapter

            viewModel.allTask.observe(viewLifecycleOwner, Observer { list ->
                if (list.isEmpty()) {
                    imageNoData.visibility = View.VISIBLE
                } else {
                    imageNoData.visibility = View.GONE
                }

                for (i in list) {
                    val state = State(
                        dataID = i.id,
                        isExpanded = 0
                    )
                    viewModel.addState(state)
                }
                recyclerAdapter.setTaskData(list)
            })

            addFab.setOnClickListener {
                Navigation.findNavController(listItemsBinding.root)
                    .navigate(R.id.navigate_to_add_Items)
            }
        }


        recyclerAdapter.onItemClickSetExpanded(object : TaskCardAdapter.SetIsExpanded {
            override fun onItemClickSetExpanded(state: State, isExpand: Int) {
                state.isExpanded = isExpand
                viewModel.updateState(state)
            }
        })

        recyclerAdapter.setStateForAdd(object : TaskCardAdapter.AddState {

            override fun onTaskAddState(id: Int, holder: TaskCardAdapter.MyTaskCardAdapter) {
                viewModel.findState(id).observe(viewLifecycleOwner) {
                    it.isExpanded =
                        if (holder.binding.taskDescriptionInfo.visibility == View.GONE) {
                            1
                        } else {
                            0
                        }
                    setVisibility(it, holder)
                }
            }

            override fun setOnLoadVisibility(id: Int, holder: TaskCardAdapter.MyTaskCardAdapter) {
                viewModel.findState(id).observe(viewLifecycleOwner) {
                    setVisibility(it, holder)
                }
            }
        })
    }

    fun setVisibility(statePosition: State, holder: TaskCardAdapter.MyTaskCardAdapter) {
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
}