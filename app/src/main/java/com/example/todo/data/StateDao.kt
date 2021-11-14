package com.example.todo.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface StateDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addState(state: State)

    @Delete
    suspend fun deleteState(state: State)

    @Query("DELETE FROM expand_state")
    suspend fun deleteAllStates()

    @Update
    suspend fun updateState(state: State)

    @Query("SELECT * FROM expand_state")
    fun readAllStates(): LiveData<List<State>>

    @Query("SELECT * FROM expand_state WHERE dataID = :id")
    fun find(id: Int): LiveData<State>
}