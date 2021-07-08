package com.example.cookbook.recipes_activity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cookbook.databinding.ItemIngredientBinding

class IngredientsAdapter(private var ingredients: MutableList<Ingredient> = ArrayList()) :
    RecyclerView.Adapter<IngredientsAdapter.IngredientItemView>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientItemView {
        val inflater = LayoutInflater.from(parent.context)
        return IngredientItemView(ItemIngredientBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: IngredientItemView, position: Int) = holder.bind(ingredients[position])

    override fun getItemCount(): Int = ingredients.size

    fun removeIngredient(position: Int) {
        ingredients.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, ingredients.size - position)
    }

    fun addIngredient(ingredient: Ingredient) {
        ingredients.add(ingredient)
        notifyItemInserted(ingredients.lastIndex)
    }

    fun setIngredients(ingredients: List<Ingredient>) {
        this.ingredients = ingredients.toMutableList()
        notifyDataSetChanged()
    }

    fun getIngredients(): List<Ingredient> = ingredients.toList()

    inner class IngredientItemView(private val binding: ItemIngredientBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnLongClickListener {

        init {
            itemView.setOnLongClickListener(this)
        }

        fun bind(ingredient: Ingredient) {
            binding.ingredientItemTextIngredientName.text = ingredient.name
        }

        override fun onLongClick(v: View?): Boolean {
            removeIngredient(layoutPosition)
            notifyItemRemoved(layoutPosition)
            return true
        }
    }
}