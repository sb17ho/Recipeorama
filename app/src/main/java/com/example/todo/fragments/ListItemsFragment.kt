package com.example.todo.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todo.R
import com.example.todo.adapter.TaskCardAdapter
import com.example.todo.databinding.FragmentListItemsBinding
import com.example.todo.viewModel.TodoViewModel

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

        // Inflate the layout for this fragment
        return listItemsBinding.root
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
                recyclerAdapter.setTaskData(list)

            })

            addFab.setOnClickListener {
                Navigation.findNavController(listItemsBinding.root)
                    .navigate(R.id.navigate_to_add_Items)
            }
        }
    }
}