package com.example.cookbook

import android.os.Bundle
import androidx.navigation.fragment.findNavController

class CreateRecipeFormFragment : RecipeFormFragment() {

    override fun navigateAfterSave(recipe: Recipe) {
        findNavController().navigate(
            R.id.navGraph_createRecipeForm_action_saveRecipe,
            Bundle().apply { putParcelable("recipe", recipe) })
    }
}