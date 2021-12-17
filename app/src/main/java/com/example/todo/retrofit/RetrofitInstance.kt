package com.example.todo.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    private val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /*
    * Creates a retrofit instance for fetching API data from MealsDB API
    * */
    val mealDBAPIServiceCreate by lazy {
        retrofit.create(MealDBAPIService::class.java)
    }
}