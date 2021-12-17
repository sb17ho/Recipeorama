package com.example.todo.retrofit

import retrofit2.http.GET
import retrofit2.http.Query

interface MealDBAPIService {

    /*
    * Search by name s=Arrabiata*/
    @GET("search.php")
    suspend fun getAllSearchRecipeByName(
        @Query("s") specifyRecipeName: String
    ): MealsData

    /*
    * Search by letter f=a
    * */
    @GET("search.php")
    suspend fun getAllSearchRecipeByLetter(
        @Query("s") specifyRecipeFirstLetter: String
    ): MealsData
}