package com.example.todo.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todo.R
import com.example.todo.adapter.TrashListAdapter
import com.example.todo.data.Task
import com.example.todo.databinding.FragmentTrashBinding
import com.example.todo.viewModel.TodoViewModel

class TrashFragment : Fragment() {
    private val viewModel: TodoViewModel by lazy { ViewModelProvider(this)[TodoViewModel::class.java] }
    private lateinit var trashBind: FragmentTrashBinding
    private val recyclerAdapter by lazy { TrashListAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        trashBind = FragmentTrashBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true) //Called to make menu item

        trashBind.apply {
            recyclerViewTrash.layoutManager = LinearLayoutManager(requireContext())
            recyclerViewTrash.adapter = recyclerAdapter

            recyclerAdapter.setOnItemClickListener(object : TrashListAdapter.OnItemClickListener {
                override fun onItemClickListener(task: Task) {
                    viewModel.deleteTask(task)
                }
            })

            viewModel.allTrashTasks.observe(viewLifecycleOwner, Observer { list ->
//            if (list.isEmpty()) {
//                imageNoData.visibility = View.VISIBLE
//            } else {
//                imageNoData.visibility = View.GONE
//            }
                recyclerAdapter.setDataList(list)
            })
        }

        return trashBind.root
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