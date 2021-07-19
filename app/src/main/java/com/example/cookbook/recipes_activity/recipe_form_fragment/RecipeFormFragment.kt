package com.example.cookbook.recipes_activity.recipe_form_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatEditText
import androidx.fragment.app.Fragment
import com.example.cookbook.databinding.FragmentRecipeFormBinding
import com.example.cookbook.recipes_activity.Ingredient
import com.example.cookbook.recipes_activity.IngredientsAdapter
import com.example.cookbook.recipes_activity.Recipe
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent

abstract class RecipeFormFragment : Fragment() {

    private var _binding: FragmentRecipeFormBinding? = null
    private val binding get() = _binding!!

    private var recipeId: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipeFormBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.recipeFormFragmentRvIngredients.apply {
            adapter = IngredientsAdapter()
            layoutManager = FlexboxLayoutManager(activity).apply { justifyContent = JustifyContent.SPACE_AROUND }
        }

        handleArguments()

        binding.recipeFormFragmentBtnAddIngredient.setOnClickListener {
            val ingredientNameInput = binding.recipeFormFragmentInputIngredientName

            if (ingredientNameInput.text.isNullOrBlank()) {
                binding.recipeFormFragmentInputLayoutIngredientName.error = "Ingredient name cannot be blank"
            } else {
                getIngredientsAdapter().addIngredient(Ingredient(ingredientNameInput.getTextAsString()))
                ingredientNameInput.text?.clear()
                binding.recipeFormFragmentInputLayoutIngredientName.isErrorEnabled = false
            }
        }

        binding.recipeFormFragmentFabSaveRecipe.setOnClickListener {
            val recipe = getRecipeFromFields()
            var valid = true

            if (recipe.name.isBlank()) {
                binding.recipeFormFragmentInputLayoutRecipeName.error = "Recipe name cannot be blank"
                valid = false
            }

            if (recipe.ingredients.isEmpty()) {
                binding.recipeFormFragmentInputLayoutIngredientName.error = "You must add at least one ingredient"
                valid = false
            }

            if (valid) {
                saveValidRecipe(recipe)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    protected abstract fun saveValidRecipe(recipe: Recipe)

    private fun handleArguments() {
        val recipe = arguments?.getParcelable<Recipe>("recipe")

        recipe?.let {
            recipeId = recipe.id
            setRecipeFields(recipe)
        }
    }

    private fun setRecipeFields(recipe: Recipe) {
        binding.recipeFormFragmentInputRecipeName.setText(recipe.name)
        binding.recipeFormFragmentInputRecipeDescription.setText(recipe.description)
        getIngredientsAdapter().setIngredients(recipe.ingredients)
    }

    private fun getRecipeFromFields(): Recipe = Recipe(
        recipeId,
        binding.recipeFormFragmentInputRecipeName.getTextAsString(),
        binding.recipeFormFragmentInputRecipeDescription.getTextAsString(),
        getIngredientsAdapter().getIngredients()
    )

    private fun getIngredientsAdapter(): IngredientsAdapter =
        binding.recipeFormFragmentRvIngredients.adapter as IngredientsAdapter

    private fun AppCompatEditText.getTextAsString(): String = this.text.toString().trim()
}