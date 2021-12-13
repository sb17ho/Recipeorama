package com.example.todo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.MainActivity
import com.example.todo.R
import com.example.todo.adapter.TaskCardAdapter
import com.example.todo.data.Task
import com.example.todo.databinding.FragmentListItemsBinding
import com.example.todo.viewModel.TodoViewModel
import com.google.android.material.snackbar.Snackbar
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator


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

            // LEFT: DELETE AND RIGHT: ARCHIVE (NOW DELETE)
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                when (direction) {
                    ItemTouchHelper.LEFT -> {
                        val removedTask: Task = recyclerAdapter.todoList[viewHolder.adapterPosition]
                        updateTask(recyclerAdapter.todoList[viewHolder.adapterPosition], 1)

                        Snackbar.make(
                            requireActivity().findViewById(android.R.id.content),
                            "${removedTask.title} Deleted",
                            Snackbar.LENGTH_LONG
                        ).setAction("Undo") {
                            updateTask(removedTask, 0)
                        }.setAnchorView(requireActivity().findViewById(R.id.bottom_nav_bar)).show()
                    }
                    ItemTouchHelper.RIGHT -> {
                        val archiveTask: Task = recyclerAdapter.todoList[viewHolder.adapterPosition]
                        updateTask(recyclerAdapter.todoList[viewHolder.adapterPosition], 1)

                        Snackbar.make(
                            requireActivity().findViewById(android.R.id.content),
                            "${archiveTask.title} Deleted",
                            Snackbar.LENGTH_LONG
                        ).setAction("Undo") {
                            updateTask(archiveTask, 0)
                        }.setAnchorView(requireActivity().findViewById(R.id.bottom_nav_bar)).show()
                    }
                }
            }
        }).attachToRecyclerView(listItemsBinding.listItem)
    }

    private fun updateTask(task: Task, isTrash: Int) {
        task.trashed = isTrash
        viewModel.updateTask(task)
    }

    private fun apply_binding_listeners() {

        listItemsBinding.apply {
            listItem.itemAnimator = SlideInUpAnimator().apply {
                addDuration = 300
            }

            listItem.layoutManager = LinearLayoutManager(requireContext())
            listItem.adapter = recyclerAdapter

            viewModel.allTask.observe(viewLifecycleOwner, Observer { list ->
                if (list.isEmpty()) {
                    imageNoData.visibility = View.VISIBLE
                } else {
                    imageNoData.visibility = View.GONE
                }
                recyclerAdapter.setTaskData(list)
            })
        }
    }

    //To override the action bar title
    override fun onResume() {
        super.onResume()
        (activity as MainActivity)
            .setActionBarTitle("Grocery List")
    }
}
