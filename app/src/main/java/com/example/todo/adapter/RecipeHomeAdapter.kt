package com.example.todo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.databinding.HomeRecipeCardBinding
import com.example.todo.fragments.RecipeListFragmentDirections
import com.example.todo.retrofit.Recipe
import com.squareup.picasso.Picasso

class RecipeHomeAdapter : RecyclerView.Adapter<RecipeHomeAdapter.MyCustomAdapter>() {
    private var recipeList = emptyList<Recipe>()

    inner class MyCustomAdapter(val homeRecipeCardBinding: HomeRecipeCardBinding) :
        RecyclerView.ViewHolder(homeRecipeCardBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCustomAdapter =
        MyCustomAdapter(
            HomeRecipeCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: MyCustomAdapter, position: Int) {
        holder.homeRecipeCardBinding.apply {
            recipeTitleId.text = recipeList[position].strMeal

            val media: String = recipeList[position].strMealThumb
            Picasso.with(imageViewHomeScreenId.context).load(media)
                .into(imageViewHomeScreenId)

            recipeScreenCardId.setOnClickListener {
                root.findNavController().navigate(
                    RecipeListFragmentDirections.actionRecipeListFragmentToRecipeDetailFragment(
                        recipeList[position]
                    )
                )
            }
        }
    }

    override fun getItemCount(): Int = recipeList.size

    fun setRecipeList(newList: List<Recipe>) {
        val diffutil = RecipeFragmentDiffUtil(recipeList, newList)
        val diffResult = DiffUtil.calculateDiff(diffutil)
        this.recipeList = newList
        diffResult.dispatchUpdatesTo(this)
    }
}