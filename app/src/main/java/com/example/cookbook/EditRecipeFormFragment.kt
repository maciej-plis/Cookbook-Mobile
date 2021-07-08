package com.example.cookbook

import android.os.Bundle
import androidx.navigation.fragment.findNavController

class EditRecipeFormFragment : RecipeFormFragment() {

    override fun navigateAfterSave(recipe: Recipe) {
            findNavController().navigate(
                R.id.navGraph_editRecipeForm_action_updateRecipe,
                Bundle().apply { putParcelable("recipe", recipe) })
    }
}