package com.example.cookbook.recipes_activity.recipe_form_fragment

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import com.example.cookbook.R
import com.example.cookbook.recipes_activity.Recipe

class CreateRecipeFormFragment : RecipeFormFragment() {

    override fun navigateAfterSave(recipe: Recipe) {
        findNavController().navigate(
            R.id.navGraph_createRecipeForm_action_saveRecipe,
            Bundle().apply { putParcelable("addRecipe", recipe) })
    }
}