package com.example.todo.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.todo.retrofit.Recipe

class RecipeFragmentDiffUtil(
    private val oldList: List<Recipe>,
    private val newList: List<Recipe>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]
}