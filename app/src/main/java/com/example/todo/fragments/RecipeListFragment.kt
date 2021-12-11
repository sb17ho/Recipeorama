package com.example.todo.fragments

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todo.MainActivity
import com.example.todo.R
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

        setHasOptionsMenu(true) //Called to make menu item
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.search_button, menu)

        val item = menu.findItem(R.id.search_items)
        val searchView = SearchView(
            (context as MainActivity).supportActionBar!!.themedContext
        )

        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW or MenuItem.SHOW_AS_ACTION_IF_ROOM)
        item.actionView = searchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                searchItem(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                searchItem("chicken")
                return true
            }
        })
    }

    private fun searchItem(name: String) {
        viewModel.getItems(name)
        viewModel.myMealsDatabase.observe(viewLifecycleOwner) {
            recipeAdapter.setRecipeList(it.meals)
        }
    }

}