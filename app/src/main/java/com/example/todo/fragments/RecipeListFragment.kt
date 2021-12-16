package com.example.todo.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todo.MainActivity
import com.example.todo.R
import com.example.todo.adapter.RecipeHomeAdapter
import com.example.todo.databinding.FragmentRecipeListBinding
import com.example.todo.viewModel.TodoViewModel
import com.google.firebase.auth.FirebaseAuth


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
        if (FirebaseAuth.getInstance().currentUser == null) {
            findNavController().navigate(RecipeListFragmentDirections.actionRecipeListFragmentToLoginFragment22())
        }


        fragmentRecipeListBinder.apply {
            recipeListRecycler.layoutManager = LinearLayoutManager(requireContext())
            recipeListRecycler.adapter = recipeAdapter

            viewModel.getItems("chicken")
            viewModel.myMealsDatabase.observe(viewLifecycleOwner) {
                if (it?.meals != null)
                    recipeAdapter.setRecipeList(it.meals)
                else
                    recipeAdapter.setRecipeList(emptyList())
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
        try {
            viewModel.myMealsDatabase.observe(viewLifecycleOwner) {
                if (it?.meals != null)
                    recipeAdapter.setRecipeList(it.meals)
                else
                    recipeAdapter.setRecipeList(emptyList())
            }
        } catch (e: Exception) {
            Log.wtf("Stack", e.message.toString())
        }
    }

    //To override the action bar title
    override fun onResume() {
        super.onResume()
        (activity as MainActivity)
            .setActionBarTitle("Recipes")
    }

}