package com.example.todo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.todo.R
import com.example.todo.data.Task
import com.example.todo.databinding.FragmentAddItemBinding
import com.example.todo.viewModel.TodoViewModel
import java.util.*

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
            taskNameEditText.doOnTextChanged { text, _, _, _ ->
                if (!text.isNullOrBlank()) {
                    inputTextLayout1.error = null
                }
            }

            priorityView.doOnTextChanged { text, _, _, _ ->
                if (!text.isNullOrBlank())
                    textInputLayout2.error = null
            }

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
            val calendar = Calendar.getInstance()
            val task: Task = Task(
                title = title,
                description = description,
                priority = viewModel.parsePriority(priority),
                dd = calendar[Calendar.DATE],
                mm = calendar[Calendar.MONTH],
                yy = calendar[Calendar.YEAR]
            )

            viewModel.addTask(task)
            findNavController().popBackStack()
            Toast.makeText(requireContext(), "Successfully Added", Toast.LENGTH_SHORT).show()
        } else {
            addFragBinding.apply {
                inputTextLayout1.error = "Text Field Required"
                textInputLayout2.error = "Text Field Required"
            }
        }
    }
}