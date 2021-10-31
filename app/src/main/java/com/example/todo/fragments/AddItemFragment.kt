package com.example.todo.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.todo.R
import com.example.todo.databinding.FragmentAddItemBinding
import com.example.todo.viewModel.TodoViewModel

class AddItemFragment : Fragment() {
    private lateinit var addFragBinding: FragmentAddItemBinding
    private val viewModel: TodoViewModel by lazy { ViewModelProvider(this)[TodoViewModel::class.java] }


    override fun onResume() {
        super.onResume()
        val priorityList = resources.getStringArray(R.array.priority_drop_down)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_textview, priorityList)
        addFragBinding.autoComplete.setAdapter(arrayAdapter)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        addFragBinding =
            FragmentAddItemBinding.inflate(inflater, container, false)

        addFragBinding.apply {

            autoComplete.setOnDismissListener {
                viewModel.dropDownListener(autoComplete.text.toString(), autoComplete)
                textInputLayout2.boxStrokeColor = autoComplete.currentTextColor
            }

            doneButton.setOnClickListener {
                findNavController().popBackStack()
            }
        }

        return addFragBinding.root
    }
}