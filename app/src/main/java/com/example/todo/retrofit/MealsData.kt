package com.example.todo.retrofit

//The JSON data contains a key meals with list of Recipe Information
data class MealsData(
    val meals: List<Recipe>
)