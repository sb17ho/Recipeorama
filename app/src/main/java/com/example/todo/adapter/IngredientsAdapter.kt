package com.example.todo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.databinding.IngredientsCardBinding

class IngredientsAdapter : RecyclerView.Adapter<IngredientsAdapter.MyCustomAdapterIngredients>() {
    private var ingredientsList = listOf<String>()
    private var databaseIngredientList = mutableSetOf<String>()

    inner class MyCustomAdapterIngredients(val ingredientsCardBinding: IngredientsCardBinding) :
        RecyclerView.ViewHolder(ingredientsCardBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCustomAdapterIngredients =
        MyCustomAdapterIngredients(
            IngredientsCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: MyCustomAdapterIngredients, position: Int) {
        holder.ingredientsCardBinding.apply {
            ingredientsTextViewId.text = ingredientsList[position]

            ingredientAddId.setOnClickListener {
                if (databaseIngredientList.contains(ingredientsList[position])) {
                    //Make Toast
                } else {
                    databaseIngredientList.add(ingredientsList[position])
                }
            }
        }
    }

    override fun getItemCount(): Int = ingredientsList.size

    fun setLists(newIngredientsList: List<String>, databaseList: MutableSet<String>) {
        this.ingredientsList = newIngredientsList
        this.databaseIngredientList = databaseList
    }
}