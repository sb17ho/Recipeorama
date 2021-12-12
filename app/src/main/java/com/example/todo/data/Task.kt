package com.example.todo.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "todo_task")
@Parcelize
data class Task(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var title: String,
    var ingredients: String,
    @ColumnInfo(defaultValue = "0") var isTrash: Int = 0,
    @ColumnInfo(defaultValue = "0") var dd: Int = 0,
    @ColumnInfo(defaultValue = "0") var mm: Int = 0,
    @ColumnInfo(defaultValue = "0") var yy: Int = 0,
    var userEmail: String
) : Parcelable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Task

        if (id != other.id) return false
        if (title != other.title) return false
        if (ingredients != other.ingredients) return false
        if (isTrash != other.isTrash) return false
        if (dd != other.dd) return false
        if (mm != other.mm) return false
        if (yy != other.yy) return false
        if (userEmail != other.userEmail) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + title.hashCode()
        result = 31 * result + ingredients.hashCode()
        result = 31 * result + isTrash
        result = 31 * result + dd
        result = 31 * result + mm
        result = 31 * result + yy
        result = 31 * result + userEmail.hashCode()
        return result
    }
}