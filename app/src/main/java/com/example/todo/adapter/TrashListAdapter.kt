package com.example.todo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.data.Task
import com.example.todo.databinding.ArchiveDeleteCardBinding
import java.text.DateFormatSymbols

class TrashListAdapter : RecyclerView.Adapter<TrashListAdapter.MyTrashListAdapter>() {

    var trashList: List<Task> = emptyList()

    inner class MyTrashListAdapter(val archiveDeleteCardBinding: ArchiveDeleteCardBinding) :
        RecyclerView.ViewHolder(archiveDeleteCardBinding.root) {
        init {
            archiveDeleteCardBinding.archiveDeleteImage.setOnClickListener {
                itemOnClick.onItemClickListener(trashList[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyTrashListAdapter =
        MyTrashListAdapter(
            ArchiveDeleteCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: MyTrashListAdapter, position: Int) {
        val (dd, mm, yy) = Triple(
            trashList[position].dd,
            trashList[position].mm,
            trashList[position].yy
        )
        val dateFormat: String = "Created on ${dd} ${DateFormatSymbols().months[mm]}, ${yy}"
        holder.archiveDeleteCardBinding.itemArchiveDeleteDate.text = dateFormat
        holder.archiveDeleteCardBinding.itemNameArchiveDelete.text = trashList[position].title
        holder.archiveDeleteCardBinding.itemArchiveDeleteDescription.text =
            trashList[position].description
        holder.archiveDeleteCardBinding.archiveDeleteImage.setImageResource(R.drawable.ic_baseline_restore_from_trash_24)
    }

    override fun getItemCount(): Int = trashList.size

    fun setDataList(newList: List<Task>) {
        val diffUtil = ItemsListDiffUtil(oldList = trashList, newList = newList)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        this.trashList = newList
        diffResult.dispatchUpdatesTo(this)
    }

    lateinit var itemOnClick: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClickListener(task: Task)
    }

    fun setOnItemClickListener(listenerUI: OnItemClickListener) {
        this.itemOnClick = listenerUI
    }

}