package com.example.todo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.todo.MainActivity
import com.example.todo.data.Task
import com.example.todo.databinding.FragmentUpdateBinding
import com.example.todo.viewModel.TodoViewModel

class UpdateFragment : Fragment() {
    private lateinit var updateFragment: FragmentUpdateBinding
    private val viewModel: TodoViewModel by lazy { ViewModelProvider(this)[TodoViewModel::class.java] }

    private val args by navArgs<UpdateFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        updateFragment = FragmentUpdateBinding.inflate(inflater, container, false)

        updateFragment.apply {

            taskNameEditText.setText(args.updateCurrentItem.title)
            taskDescription.setText(args.updateCurrentItem.ingredients)

            doneButton.setOnClickListener {
                updateDatabaseItem()
                findNavController().popBackStack()
            }
        }
        return updateFragment.root
    }

    fun updateDatabaseItem() {
        val title = updateFragment.taskNameEditText.text.toString()
        val ingredients = updateFragment.taskDescription.text.toString()

        if (!viewModel.checkIfNotEmpty(title)) {

            val task: Task = args.updateCurrentItem
            task.title = title
            task.ingredients = ingredients
            viewModel.updateTask(task)
            findNavController().popBackStack()
            Toast.makeText(requireContext(), "Successfully Updated", Toast.LENGTH_SHORT).show()
        } else {
            updateFragment.apply {
                inputTextLayout1.error = "Text Field Required"
            }
        }
    }

    //To override the action bar title
    override fun onResume() {
        super.onResume()
        (activity as MainActivity)
            .setActionBarTitle("Update")
    }
}