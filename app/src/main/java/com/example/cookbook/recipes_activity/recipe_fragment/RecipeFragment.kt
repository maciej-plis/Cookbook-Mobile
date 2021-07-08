package com.example.cookbook.recipes_activity.recipe_fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.cookbook.R
import com.example.cookbook.databinding.FragmentRecipeBinding
import com.example.cookbook.recipes_activity.IngredientsAdapter
import com.example.cookbook.recipes_activity.Recipe
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent.SPACE_AROUND
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class RecipeFragment : Fragment() {

    private var _binding: FragmentRecipeBinding? = null
    private val binding get() = _binding!!

    private var recipe: Recipe? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        setHasOptionsMenu(true)
        handleInputArgs()

        binding.recipeFragmentRvIngredients.apply {
            adapter = IngredientsAdapter()
            layoutManager = FlexboxLayoutManager(activity).apply { justifyContent = SPACE_AROUND }
        }

        setRecipeFields()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_recipe, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_recipe_editRecipe -> {
                findNavController().navigate(
                    R.id.navGraph_recipeFragment_action_editRecipe,
                    Bundle().apply { putParcelable("recipe", recipe) })
                return true
            }
            R.id.menu_recipe_deleteRecipe -> {
                showDeleteConfirmationDialog()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showDeleteConfirmationDialog() = context?.let {
        MaterialAlertDialogBuilder(it)
            .setMessage("Are you sure you want delete this recipe")
            .setPositiveButton("Yes") { _, _ ->
                findNavController().navigate(
                    R.id.navGraph_recipeFragment_action_deleteRecipe,
                    Bundle().apply { putParcelable("recipe", recipe) })
            }
            .setNegativeButton("No") { _, _ -> }
            .show()
    }

    private fun setRecipeFields() = recipe?.let {
        binding.run {
            recipeFragmentTextRecipeName.text = it.name
            recipeFragmentTextRecipeDescription.text = it.description
            getIngredientsAdapter().setIngredients(it.ingredients)
        }
    }

    private fun handleInputArgs() = arguments?.run {
        getParcelable<Recipe>("recipe")?.let { recipe = it }
        clear()
    }

    private fun getIngredientsAdapter(): IngredientsAdapter =
        binding.recipeFragmentRvIngredients.adapter as IngredientsAdapter
}