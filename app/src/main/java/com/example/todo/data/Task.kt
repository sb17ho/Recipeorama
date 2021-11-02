package com.example.todo.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.todo.priorityClasses.Priority
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "todo_task")
@Parcelize
data class Task(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var title: String,
    var description: String,
    var priority: Priority
): Parcelable