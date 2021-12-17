package com.example.todo.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.todo.data.Task
import com.example.todo.data.TaskDatabase
import com.example.todo.dialog.PleaseWaitDialog
import com.example.todo.retrofit.MealsData
import com.example.todo.retrofit.Recipe
import com.example.todo.retrofit.RetrofitInstance
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.jetbrains.anko.doAsync
import org.json.JSONObject

/*
* The purpose of this view model to handle every backend calls separating
* the calls from the UI framework for easy backend navigation and readability
* It handles most calls to Firebase, Room Database, Retrofit Calls and making use of
* Kotlin view model scope and LiveData to efficient backend processing
* */
class TodoViewModel(application: Application) : AndroidViewModel(application) {

    private val taskDatabase: TaskDatabase by lazy {
        TaskDatabase.getTaskDatabase(application.applicationContext)
    }

    private val db: FirebaseFirestore = FirebaseFirestore.getInstance();
    private val USERS_COLLECTION = "Users"
    private val ITEMS_COLLECTIONS = "Items"
    private val GROCERY_ITEMS = "Grocery"

    //Retrofit
    var myMealsDatabase: MutableLiveData<MealsData> = MutableLiveData()
    var myMeals: MutableLiveData<List<Recipe>> = MutableLiveData()

    val allTask = taskDatabase.taskDao().readAllTask(0, getCurrentUserEmail().toString())
    val allTrashTasks = taskDatabase.taskDao().readAllTask(1, getCurrentUserEmail().toString())

    /*
    * Add the recipe to the Room Database
    * */
    fun addTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            taskDatabase.taskDao()
                .addTask(task)
        }
    }

    /*
    * Delete the Recipe Item as requested by the user
    * */
    fun deleteTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            taskDatabase.taskDao()
                .deleteTask(task.id, getCurrentUserEmail().toString())
        }
    }

    /*
    * To Delete all the Trash Items
    * */
    fun deleteAllTask() {
        viewModelScope.launch(Dispatchers.IO) {
            taskDatabase.taskDao().deleteAllTask(getCurrentUserEmail().toString())
        }
    }

    /*
    * To update the Recipe based on user information
    * */
    fun updateTask(task: Task) {
        viewModelScope.launch {
            taskDatabase.taskDao().updateTask(task)
        }
    }

    fun checkIfNotEmpty(title: String): Boolean {
        return title.isEmpty()
    }

    /*
    * To fetch recipe items from mealsDB using Retrofit API
    * */
    fun getItems(name: String) {
        viewModelScope.launch {
            val items = RetrofitInstance().mealDBAPIServiceCreate.getAllSearchRecipeByName(
                specifyRecipeName = name
            )
            myMealsDatabase.value = items
            myMeals.value = myMealsDatabase.value?.meals
        }
    }

    /*
    * Backing up the data to Firebase from Room Database
    * */
    fun backupList(listOfItems: List<Task>, pleaseWaitDialog: PleaseWaitDialog) {
        doAsync {
            db.collection(USERS_COLLECTION)
                .document(getCurrentUserEmail().toString())
                .collection(ITEMS_COLLECTIONS)
                .document(GROCERY_ITEMS)
                .set(hashMapOf(GROCERY_ITEMS to listOfItems))

            pleaseWaitDialog.closeDialog()
        }
    }

    /*
    * Restoring data from firebase into Room Database
    * */
    fun restoreList(pleaseWaitDialog: PleaseWaitDialog) {
        doAsync {
            db.collection(USERS_COLLECTION)
                .document(getCurrentUserEmail().toString())
                .collection(ITEMS_COLLECTIONS)
                .document(GROCERY_ITEMS)
                .get()
                .addOnSuccessListener {
                    val items = it.get(GROCERY_ITEMS) as List<MutableMap<String, Any>>
                    items.forEach {
                        addTask(Json.decodeFromString(JSONObject(it as Map<String, Any>).toString()))
                    }
                }
            pleaseWaitDialog.closeDialog()
        }
    }

    fun getCurrentUserEmail() = FirebaseAuth.getInstance().currentUser?.email

    fun getCurrentUserName() = FirebaseAuth.getInstance().currentUser?.displayName
}
