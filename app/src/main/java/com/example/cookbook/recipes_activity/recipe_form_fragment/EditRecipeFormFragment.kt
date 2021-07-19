package com.example.cookbook.recipes_activity.recipe_form_fragment

import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.navigation.fragment.findNavController
import com.example.cookbook.R
import com.example.cookbook.RetrofitEventListener
import com.example.cookbook.recipes_activity.Recipe
import com.example.cookbook.recipes_activity.RecipesApiRestClient
import retrofit2.Call

class EditRecipeFormFragment : RecipeFormFragment() {

    override fun saveValidRecipe(recipe: Recipe) {
        RecipesApiRestClient.updateRecipe(recipe, UpdateRecipeEventListener(recipe.id!!))
    }

    inner class UpdateRecipeEventListener(private val recipeId: String) : RetrofitEventListener<Unit> {
        override fun onSuccess(call: Call<Unit>, response: Unit) {
            findNavController().navigate(
                R.id.navGraph_editRecipeForm_action_updateRecipe,
                Bundle().apply { putString("recipeId", recipeId) }
            )
        }

        override fun onError(call: Call<Unit>, t: Throwable) {
            Toast.makeText(activity, "Failed to save updated recipe", LENGTH_SHORT).show()
        }
    }
}