package com.example.cookbook.recipes_activity.recipe_fragment

import android.os.Bundle
import android.view.*
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.cookbook.R
import com.example.cookbook.RetrofitEventListener
import com.example.cookbook.databinding.FragmentRecipeBinding
import com.example.cookbook.recipes_activity.IngredientsAdapter
import com.example.cookbook.recipes_activity.Recipe
import com.example.cookbook.recipes_activity.RecipesApiRestClient
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent.SPACE_AROUND
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import retrofit2.Call

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

        binding.recipeFragmentRvIngredients.apply {
            adapter = IngredientsAdapter()
            layoutManager = FlexboxLayoutManager(activity).apply { justifyContent = SPACE_AROUND }
        }

        setHasOptionsMenu(true)
        handleArguments()
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

    private fun showDeleteConfirmationDialog() {
        if(context != null && recipe != null) {
            MaterialAlertDialogBuilder(requireContext())
                .setMessage("Are you sure you want delete this recipe")
                .setPositiveButton("Yes") { _, _ ->
                    RecipesApiRestClient.deleteRecipe(recipe!!.id!!, DeleteRecipeEventListener())
                }
                .setNegativeButton("No") { _, _ -> }
                .show()
        }
    }

    private fun setRecipeFields() = recipe?.let {
        binding.recipeFragmentTextRecipeName.text = it.name
        binding.recipeFragmentTextRecipeDescription.text = it.description
        getIngredientsAdapter().setIngredients(it.ingredients)
    }

    private fun handleArguments() {
        val recipeId = arguments?.getString("recipeId")

        if (recipeId != null) {
            RecipesApiRestClient.getRecipe(recipeId, GetRecipeEventListener())
        } else {
            Toast.makeText(activity, "Couldn't load recipe", LENGTH_SHORT).show()
        }
    }

    private fun getIngredientsAdapter(): IngredientsAdapter =
        binding.recipeFragmentRvIngredients.adapter as IngredientsAdapter

    inner class GetRecipeEventListener : RetrofitEventListener<Recipe> {
        override fun onSuccess(call: Call<Recipe>, response: Recipe) {
            recipe = response
            setRecipeFields()
        }

        override fun onError(call: Call<Recipe>, t: Throwable) {
            Toast.makeText(activity, "Couldn't load recipe", LENGTH_SHORT).show()
        }
    }

    inner class DeleteRecipeEventListener : RetrofitEventListener<Unit> {
        override fun onSuccess(call: Call<Unit>, response: Unit) {
            findNavController().navigate(R.id.navGraph_recipeFragment_action_deleteRecipe)
        }

        override fun onError(call: Call<Unit>, t: Throwable) {
            Toast.makeText(activity, "Couldn't delete recipe", LENGTH_SHORT).show()
        }
    }
}