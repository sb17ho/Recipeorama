package com.example.todo.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todo.R
import com.example.todo.adapter.ArchiveListAdapter
import com.example.todo.data.Task
import com.example.todo.databinding.FragmentArchiveBinding
import com.example.todo.viewModel.TodoViewModel

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
                    task.isArchived = 0
                    viewModel.updateTask(task)
                }
            })

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