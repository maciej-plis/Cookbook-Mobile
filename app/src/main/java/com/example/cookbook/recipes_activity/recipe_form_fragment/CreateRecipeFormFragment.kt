package com.example.cookbook.recipes_activity.recipe_form_fragment

import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.navigation.fragment.findNavController
import com.example.cookbook.R
import com.example.cookbook.RetrofitEventListener
import com.example.cookbook.recipes_activity.Recipe
import com.example.cookbook.recipes_activity.RecipesApiRestClient
import retrofit2.Call

class CreateRecipeFormFragment : RecipeFormFragment() {

    override fun saveValidRecipe(recipe: Recipe) {
        RecipesApiRestClient.saveRecipe(recipe, SaveRecipeEventListener())
    }

    inner class SaveRecipeEventListener : RetrofitEventListener<Unit> {
        override fun onSuccess(call: Call<Unit>, response: Unit) {
            findNavController().navigate(R.id.navGraph_createRecipeForm_action_saveRecipe)
        }

        override fun onError(call: Call<Unit>, t: Throwable) {
            Toast.makeText(activity, "Failed to save recipe", LENGTH_SHORT).show()
        }
    }
}