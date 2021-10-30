package com.example.todo.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Query("DELETE FROM todo_task")
    suspend fun deleteAllTask()

    /*TODO: Handle Update*/

    @Query("SELECT * FROM todo_task")
    fun readAllTask(): LiveData<List<Task>>
}
