package com.example.todo.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "expand_state")
@Parcelize
data class State(
    @PrimaryKey(autoGenerate = false)
    var dataID: Int,
    var isExpanded: Int
) : Parcelable