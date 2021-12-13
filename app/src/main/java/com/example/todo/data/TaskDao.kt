package com.example.todo.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTask(task: Task)

    @Query("DELETE FROM todo_task WHERE id = :id AND userEmail = :userEmail")
    suspend fun deleteTask(id: Int, userEmail: String)

    @Query("DELETE FROM todo_task WHERE userEmail = :userEmail")
    suspend fun deleteAllTask(userEmail: String)

    @Update
    suspend fun updateTask(task: Task)

    @Query("SELECT * FROM todo_task WHERE trashed = :isTrash AND userEmail = :userEmail")
    fun readAllTask(isTrash: Int, userEmail: String): LiveData<List<Task>>
}
