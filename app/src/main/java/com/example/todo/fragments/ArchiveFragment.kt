package com.example.todo.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.adapter.ArchiveListAdapter
import com.example.todo.data.Task
import com.example.todo.databinding.FragmentArchiveBinding
import com.example.todo.viewModel.TodoViewModel
import com.google.android.material.snackbar.Snackbar
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator

//TODO Remove
class ArchiveFragment : Fragment() {
    private val viewModel: TodoViewModel by lazy { ViewModelProvider(this)[TodoViewModel::class.java] }
    private lateinit var archiveFragmentBind: FragmentArchiveBinding
    private val recyclerAdapter by lazy { ArchiveListAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        archiveFragmentBind = FragmentArchiveBinding.inflate(inflater, container, false)

        setHasOptionsMenu(true) //Called to make menu item

        archiveFragmentBind.apply {
            archiveRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            archiveRecyclerView.adapter = recyclerAdapter

            recyclerAdapter.setOnItemClickListener(object : ArchiveListAdapter.OnItemClickListener {
                override fun onItemClickListener(task: Task) {
                    viewModel.updateTask(task)
                }
            })

            applySwipeGesture()
            archiveFragmentBind.archiveRecyclerView.itemAnimator = SlideInUpAnimator().apply {
                addDuration = 300
            }

            viewModel.allArchivedTasks.observe(viewLifecycleOwner, Observer { list ->
//            if (list.isEmpty()) {
//                imageNoData.visibility = View.VISIBLE
//            } else {
//                imageNoData.visibility = View.GONE
//            }
                recyclerAdapter.setDataList(list)
            })
        }

        return archiveFragmentBind.root
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

            // LEFT: DELETE AND RIGHT: DELETE
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                when (direction) {
                    ItemTouchHelper.LEFT -> {
                        val removedTask: Task =
                            recyclerAdapter.archiveList[viewHolder.adapterPosition]
                        removedTask.trashed = 1
                        viewModel.updateTask(removedTask)

                        Snackbar.make(
                            requireActivity().findViewById(android.R.id.content),
                            "${removedTask.title} Deleted",
                            Snackbar.LENGTH_LONG
                        ).setAnchorView(requireActivity().findViewById(R.id.bottom_nav_bar)).show()
                    }
                    ItemTouchHelper.RIGHT -> {
                        val removedTask: Task =
                            recyclerAdapter.archiveList[viewHolder.adapterPosition]
                        removedTask.trashed = 1
                        viewModel.updateTask(removedTask)

                        Snackbar.make(
                            requireActivity().findViewById(android.R.id.content),
                            "${removedTask.title} Archived",
                            Snackbar.LENGTH_LONG
                        ).setAnchorView(requireActivity().findViewById(R.id.bottom_nav_bar)).show()
                    }
                }
            }
        }).attachToRecyclerView(archiveFragmentBind.archiveRecyclerView)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_all_nav, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.delete_all) {
            viewModel.deleteAllTask()
        }
        return super.onOptionsItemSelected(item)
    }
}