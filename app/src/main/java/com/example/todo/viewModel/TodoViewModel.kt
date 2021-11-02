package com.example.todo.viewModel

import android.app.Application
import android.widget.AutoCompleteTextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.R
import com.example.todo.data.Task
import com.example.todo.data.TaskDatabase
import com.example.todo.priorityClasses.Priority
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoViewModel(application: Application) : AndroidViewModel(application) {

    private val taskDatabase: TaskDatabase by lazy {
        TaskDatabase.getTaskDatabase(application.applicationContext)
    }

    val allTask = taskDatabase.taskDao().readAllTask()

    fun addTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            taskDatabase.taskDao()
                .addTask(task)
        }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            taskDatabase.taskDao().updateTask(task)
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            taskDatabase.taskDao()
                .deleteTask(task)
        }
    }

    fun deleteAllTask() {
        viewModelScope.launch(Dispatchers.IO) {
            taskDatabase.taskDao().deleteAllTask()
        }
    }

    fun checkIfNotEmpty(title: String, priority: String): Boolean {
        return title.isEmpty() && priority.isEmpty()
    }

    fun parsePriority(priority: String): Priority {
        return when (priority.lowercase()) {
            "low" -> Priority.LOW
            "medium" -> Priority.MEDIUM
            "high" -> Priority.HIGH
            else -> Priority.NONE
        }
    }

    fun dropDownListener(priority: String, textView: AutoCompleteTextView) {
        when (priority.lowercase()) {
            "none" -> textView.setTextColor(
                ContextCompat.getColor(
                    getApplication(),
                    R.color.black
                )
            )
            "low" -> textView.setTextColor(
                ContextCompat.getColor(
                    getApplication(),
                    R.color.green
                )
            )
            "medium" -> textView.setTextColor(
                ContextCompat.getColor(
                    getApplication(),
                    R.color.orange
                )
            )
            "high" -> textView.setTextColor(
                ContextCompat.getColor(
                    getApplication(),
                    R.color.red
                )
            )
        }
    }
}
