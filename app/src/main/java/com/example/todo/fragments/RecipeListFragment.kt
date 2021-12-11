package com.example.todo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todo.adapter.RecipeHomeAdapter
import com.example.todo.databinding.FragmentRecipeListBinding
import com.example.todo.viewModel.TodoViewModel

class RecipeListFragment : Fragment() {
    private lateinit var fragmentRecipeListBinder: FragmentRecipeListBinding
    private val viewModel by lazy {
        ViewModelProvider(this)[TodoViewModel::class.java]
    }
    private val recipeAdapter by lazy {
        RecipeHomeAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentRecipeListBinder =
            FragmentRecipeListBinding.inflate(layoutInflater, container, false)

        fragmentRecipeListBinder.apply {
            recipeListRecycler.layoutManager = LinearLayoutManager(requireContext())
            recipeListRecycler.adapter = recipeAdapter

            viewModel.getItems("chicken")
            viewModel.myMealsDatabase.observe(viewLifecycleOwner) {
                recipeAdapter.setRecipeList(it.meals)
            }

        }

        return fragmentRecipeListBinder.root
    }

}