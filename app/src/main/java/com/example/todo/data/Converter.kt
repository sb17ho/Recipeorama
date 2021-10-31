package com.example.todo.data

import androidx.room.TypeConverter
import com.example.todo.priorityClasses.Priority

class Converter {

    @TypeConverter
    fun fromPriority(priority: Priority) = priority.name

    @TypeConverter
    fun toPriority(priority: String) = Priority.valueOf(priority)
}