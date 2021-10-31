package com.example.todo.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.todo.priorityClasses.Priority

@Entity(tableName = "todo_task")
data class Task(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var title: String,
    var description: String,
    var priority: Priority
)