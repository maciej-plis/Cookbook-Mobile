package com.example.cookbook.recipes_activity

import retrofit2.Call
import retrofit2.http.*
import java.util.*

interface RecipesApi {

    @GET("/recipes")
    fun getRecipes(): Call<List<Recipe>>

    @GET("/recipes/{recipeId}")
    fun getRecipe(@Path("recipeId") recipeId: String): Call<Recipe>

    @POST("/recipes")
    fun saveRecipe(@Body recipe: Recipe): Call<Unit>

    @PUT("/recipes/{recipeId}")
    fun updateRecipe(@Path("recipeId") recipeId: String, @Body recipe: Recipe): Call<Unit>

    @DELETE("/recipes/{recipeId}")
    fun deleteRecipe(@Path("recipeId") recipeID: String): Call<Unit>
}