package com.example.cookbook.recipes_activity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Recipe(val name: String, val description: String, val ingredients: List<Ingredient>) : Parcelable