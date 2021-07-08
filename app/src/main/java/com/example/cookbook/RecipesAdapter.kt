package com.example.cookbook

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.cookbook.databinding.ItemRecipeBinding

class RecipesAdapter(private val recipes: MutableList<Recipe>) :
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
                Bundle().apply { putParcelable("recipe", recipes[layoutPosition]) })
        }
    }
}

