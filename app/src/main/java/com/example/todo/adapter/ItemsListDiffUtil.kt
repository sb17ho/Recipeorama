package com.example.todo.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.todo.data.Task

class ItemsListDiffUtil(
    private val oldList: List<Task>,
    private val newList: List<Task>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList[newItemPosition]
}