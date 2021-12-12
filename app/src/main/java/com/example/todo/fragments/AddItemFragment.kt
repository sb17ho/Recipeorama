package com.example.todo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.todo.data.Task
import com.example.todo.databinding.FragmentAddItemBinding
import com.example.todo.priorityClasses.Priority
import com.example.todo.viewModel.TodoViewModel
import java.util.*

//TODO Most probably not required
class AddItemFragment : Fragment() {
    private lateinit var addFragBinding: FragmentAddItemBinding
    private val viewModel: TodoViewModel by lazy { ViewModelProvider(this)[TodoViewModel::class.java] }
    
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

            doneButton.setOnClickListener {
                insertDataToDatabase()
            }
        }

        return addFragBinding.root
    }

    fun insertDataToDatabase() {
        val title = addFragBinding.taskNameEditText.text.toString()
        val description = addFragBinding.taskDescription.text.toString()

        if (!viewModel.checkIfNotEmpty(title)) {

            val calendar = Calendar.getInstance()
            val task: Task = Task(
                title = title,
                ingredients = description,
                dd = calendar[Calendar.DATE],
                mm = calendar[Calendar.MONTH],
                yy = calendar[Calendar.YEAR],
                userEmail = "null"
            )

            viewModel.addTask(task)
            findNavController().popBackStack()
            Toast.makeText(requireContext(), "Successfully Added", Toast.LENGTH_SHORT).show()
        } else {
            addFragBinding.apply {
                inputTextLayout1.error = "Text Field Required"
            }
        }
    }
}