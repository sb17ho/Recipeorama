package com.example.todo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.todo.databinding.FragmentRecipeDetailBinding

class RecipeDetailFragment : Fragment() {
    private lateinit var recipeDetailFragment: FragmentRecipeDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        recipeDetailFragment = FragmentRecipeDetailBinding.inflate(layoutInflater, container, false)



        return recipeDetailFragment.root
    }

}