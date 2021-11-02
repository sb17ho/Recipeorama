package com.example.todo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.todo.R
import com.example.todo.data.Task
import com.example.todo.databinding.FragmentAddItemBinding
import com.example.todo.viewModel.TodoViewModel

class AddItemFragment : Fragment() {
    private lateinit var addFragBinding: FragmentAddItemBinding
    private val viewModel: TodoViewModel by lazy { ViewModelProvider(this)[TodoViewModel::class.java] }

    override fun onResume() {
        super.onResume()
        val priorityList = resources.getStringArray(R.array.priority_drop_down)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_textview, priorityList)
        addFragBinding.priorityView.setAdapter(arrayAdapter)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        addFragBinding =
            FragmentAddItemBinding.inflate(inflater, container, false)

        addFragBinding.apply {

            priorityView.setOnDismissListener {
                viewModel.dropDownListener(priorityView.text.toString(), priorityView)
                textInputLayout2.boxStrokeColor = priorityView.currentTextColor
            }

            doneButton.setOnClickListener {
                insertDataToDatabase()
            }
        }

        return addFragBinding.root
    }

    fun insertDataToDatabase() {
        val title = addFragBinding.taskNameEditText.text.toString()
        val description = addFragBinding.taskDescription.text.toString()
        val priority = addFragBinding.priorityView.text.toString()

        if (!viewModel.checkIfNotEmpty(title, priority)) {
            viewModel.addTask(
                Task(
                    title = title,
                    description = description,
                    priority = viewModel.parsePriority(priority)
                )
            )
            findNavController().popBackStack()
            Toast.makeText(requireContext(), "Successfully Added", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "Please Fill out all Fields", Toast.LENGTH_SHORT).show()
        }
    }
}