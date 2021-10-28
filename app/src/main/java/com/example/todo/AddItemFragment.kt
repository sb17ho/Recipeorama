package com.example.todo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.todo.databinding.FragmentAddItemBinding

class AddItemFragment : Fragment() {
    private lateinit var addFragBinding: FragmentAddItemBinding

    override fun onResume() {
        super.onResume()
        val priorityList = resources.getStringArray(R.array.priority_drop_down)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_textview, priorityList)
        addFragBinding.autoComplete.setAdapter(arrayAdapter)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        addFragBinding =
            FragmentAddItemBinding.inflate(inflater, container, false)

        addFragBinding.doneButton.setOnClickListener {
            findNavController().popBackStack()
        }

        return addFragBinding.root
    }
}