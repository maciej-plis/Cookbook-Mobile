package com.example.cookbook.recipes_activity.recipes_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.cookbook.R
import com.example.cookbook.databinding.ItemRecipeBinding
import com.example.cookbook.recipes_activity.Recipe

class RecipesAdapter(private var recipes: MutableList<Recipe> = ArrayList()) :
    RecyclerView.Adapter<RecipesAdapter.RecipeItemView>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeItemView {
        val inflater = LayoutInflater.from(parent.context)
        return RecipeItemView(ItemRecipeBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: RecipeItemView, position: Int) = holder.bind(recipes[position])

    override fun getItemCount(): Int = recipes.size

    fun addRecipe(recipe: Recipe) {
        recipes.add(recipe)
        notifyItemInserted(recipes.lastIndex)
    }

    fun setRecipes(recipes: List<Recipe>) {
        this.recipes = recipes.toMutableList()
        notifyDataSetChanged()
    }

    inner class RecipeItemView(private val binding: ItemRecipeBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(recipe: Recipe) {
            binding.recipeItemTextRecipeName.text = recipe.name
        }

        override fun onClick(v: View?) {
            itemView.findNavController().navigate(
                R.id.navGraph_recipesFragment_action_showRecipe,
                Bundle().apply { putString("recipeId", recipes[layoutPosition].id) })
        }
    }
}

