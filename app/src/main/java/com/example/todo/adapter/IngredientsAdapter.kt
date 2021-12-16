package com.example.todo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.databinding.IngredientsCardBinding

class IngredientsAdapter : RecyclerView.Adapter<IngredientsAdapter.MyCustomAdapterIngredients>() {
    private var ingredientsList = listOf<String>()

    inner class MyCustomAdapterIngredients(val ingredientsCardBinding: IngredientsCardBinding) :
        RecyclerView.ViewHolder(ingredientsCardBinding.root) {
        init {
            ingredientsCardBinding.ingredientsCardId.setOnClickListener {
                itemOnClick.onItemClickListener(ingredientsList[adapterPosition])
            }
        }
    }

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
        }
    }

    override fun getItemCount(): Int = ingredientsList.size

    fun setList(newIngredientsList: List<String>) {
        this.ingredientsList = newIngredientsList
        notifyDataSetChanged()
    }

    lateinit var itemOnClick: IngredientsAdapter.OnItemClickListener

    interface OnItemClickListener {
        fun onItemClickListener(ingredient: String)
    }

    fun setOnItemClickListener(listenerUI: IngredientsAdapter.OnItemClickListener) {
        this.itemOnClick = listenerUI
    }

}