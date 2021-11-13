package com.example.todo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.data.Task
import com.example.todo.databinding.ArchiveDeleteCardBinding
import java.text.DateFormatSymbols

class ArchiveListAdapter : RecyclerView.Adapter<ArchiveListAdapter.MyArchiveListAdapter>() {
    var archiveList: List<Task> = emptyList()

    inner class MyArchiveListAdapter(val archiveDeleteBinding: ArchiveDeleteCardBinding) :
        RecyclerView.ViewHolder(archiveDeleteBinding.root) {
        init {
            archiveDeleteBinding.archiveDeleteImage.setOnClickListener {
                itemOnClick.onItemClickListener(archiveList[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyArchiveListAdapter =
        MyArchiveListAdapter(
            ArchiveDeleteCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: MyArchiveListAdapter, position: Int) {
        val (dd, mm, yy) = Triple(
            archiveList[position].dd,
            archiveList[position].mm,
            archiveList[position].yy
        )
        val dateFormat: String = "Created on ${dd} ${DateFormatSymbols().months[mm]}, ${yy}"
        holder.archiveDeleteBinding.itemArchiveDeleteDate.text = dateFormat
        holder.archiveDeleteBinding.itemNameArchiveDelete.text = archiveList[position].title
        holder.archiveDeleteBinding.itemArchiveDeleteDescription.text =
            archiveList[position].description
        holder.archiveDeleteBinding.archiveDeleteImage.setImageResource(R.drawable.ic_baseline_archive_24)
    }

    override fun getItemCount(): Int = archiveList.size

    fun setDataList(newList: List<Task>) {
        val diffUtil = ItemsListDiffUtil(oldList = archiveList, newList = newList)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        this.archiveList = newList
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