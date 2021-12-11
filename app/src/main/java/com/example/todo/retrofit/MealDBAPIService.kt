package com.example.todo.retrofit

import retrofit2.http.GET
import retrofit2.http.Query

interface MealDBAPIService {

    //s=Arrabiata
    @GET("search.php")
    suspend fun getAllSearchRecipeByName(
        @Query("s") specifyRecipeName: String
    ): MealsData

    //f=a
    @GET("search.php")
    suspend fun getAllSearchRecipeByLetter(
        @Query("s") specifyRecipeFirstLetter: String
    ): MealsData
}