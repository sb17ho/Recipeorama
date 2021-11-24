package com.example.todo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.todo.R
import com.example.todo.data.Task
import com.example.todo.databinding.FragmentUpdateBinding
import com.example.todo.priorityClasses.Priority
import com.example.todo.viewModel.TodoViewModel

class UpdateFragment : Fragment() {
    private lateinit var updateFragment: FragmentUpdateBinding
    private val viewModel: TodoViewModel by lazy { ViewModelProvider(this)[TodoViewModel::class.java] }

    private val args by navArgs<UpdateFragmentArgs>()

    override fun onResume() {
        super.onResume()
        val priorityList = resources.getStringArray(R.array.priority_drop_down)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_textview, priorityList)
        updateFragment.priorityView.setAdapter(arrayAdapter)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        updateFragment = FragmentUpdateBinding.inflate(inflater, container, false)

        updateFragment.apply {

            taskNameEditText.setText(args.updateCurrentItem.title)
            priorityView.setText(priorityViewParse(args.updateCurrentItem.priority))
            taskDescription.setText(args.updateCurrentItem.description)

            priorityView.setOnDismissListener {
                viewModel.dropDownListener(priorityView.text.toString(), priorityView)
                textInputLayout2.boxStrokeColor = priorityView.currentTextColor
            }

            doneButton.setOnClickListener {
                updateDatabaseItem()
                findNavController().popBackStack()
            }
        }
        return updateFragment.root
    }

    fun priorityViewParse(priority: Priority): String {
        var result: String = "None"

        when (priority.name.lowercase()) {
            "low" -> {
                updateFragment.textInputLayout2.boxStrokeColor =
                    ContextCompat.getColor(updateFragment.textInputLayout2.context, R.color.green)
                result = "Low"
            }
            "medium" -> {
                updateFragment.textInputLayout2.boxStrokeColor =
                    ContextCompat.getColor(updateFragment.textInputLayout2.context, R.color.orange)
                result = "Medium"
            }
            "high" -> {
                updateFragment.textInputLayout2.boxStrokeColor =
                    ContextCompat.getColor(updateFragment.textInputLayout2.context, R.color.red)
                result = "High"
            }
        }
        return result
    }

    fun updateDatabaseItem() {
        val title = updateFragment.taskNameEditText.text.toString()
        val description = updateFragment.taskDescription.text.toString()
        var priority = updateFragment.priorityView.text.toString()

        if (!viewModel.checkIfNotEmpty(title)) {
            if (priority.isBlank()){
                priority = "low"
            }

            val task: Task = args.updateCurrentItem
            task.title = title
            task.description = description
            task.priority = viewModel.parsePriority(priority)

            viewModel.updateTask(task)
            findNavController().popBackStack()
            Toast.makeText(requireContext(), "Successfully Updated", Toast.LENGTH_SHORT).show()
        } else {
            updateFragment.apply {
                inputTextLayout1.error = "Text Field Required"
            }
        }
    }

}