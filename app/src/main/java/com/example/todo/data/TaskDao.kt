package com.example.todo.data

import androidx.lifecycle.LiveData
import androidx.room.Query

interface TaskDao {

    suspend fun addTask(task: Task)

    /*Add delete and update*/

    @Query("SELECT * FROM todo_task")
    fun readAllTask(): LiveData<List<Task>>
}