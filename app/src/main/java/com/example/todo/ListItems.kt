package com.example.todo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.todo.databinding.FragmentListItemsBinding

class ListItems : Fragment() {
    private lateinit var listItemsBinding: FragmentListItemsBinding
    private val viewModel: TodoViewModel by lazy { ViewModelProvider(this)[TodoViewModel::class.java] }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        listItemsBinding = FragmentListItemsBinding.inflate(inflater, container, false)

        listItemsBinding.addFab.setOnClickListener {
            Navigation.findNavController(listItemsBinding.root).navigate(R.id.navigate_to_add_Items)
        }

        // Inflate the layout for this fragment
        return listItemsBinding.root
    }
}