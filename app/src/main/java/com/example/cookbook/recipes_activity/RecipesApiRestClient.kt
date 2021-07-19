package com.example.cookbook.recipes_activity

import com.example.cookbook.NetworkClient
import com.example.cookbook.RetrofitEventListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object RecipesApiRestClient {

    private var recipesApi: RecipesApi = NetworkClient.retrofitClient.create(RecipesApi::class.java)

    fun getRecipes(eventListener: RetrofitEventListener<List<Recipe>>) {
        recipesApi.getRecipes().enqueue(callback(eventListener))
    }

    fun getRecipe(recipeId: String, eventListener: RetrofitEventListener<Recipe>) {
        recipesApi.getRecipe(recipeId).enqueue(callback(eventListener))
    }

    fun saveRecipe(recipe: Recipe, eventListener: RetrofitEventListener<Unit>) {
        recipesApi.saveRecipe(recipe).enqueue(callback(eventListener))
    }

    fun updateRecipe(recipe: Recipe, eventListener: RetrofitEventListener<Unit>) {
        recipesApi.updateRecipe(recipe.id!!, recipe).enqueue(callback(eventListener))
    }

    fun deleteRecipe(recipeId: String, eventListener: RetrofitEventListener<Unit>) {
        recipesApi.deleteRecipe(recipeId).enqueue(callback(eventListener))
    }

    private fun <T> callback(eventListener: RetrofitEventListener<T>) = object : Callback<T> {
        override fun onResponse(call: Call<T>, response: Response<T>) {
            response.body()?.let {
                eventListener.onSuccess(call, it)
            }
        }

        override fun onFailure(call: Call<T>, t: Throwable) {
            eventListener.onError(call, t)
        }

    }
}