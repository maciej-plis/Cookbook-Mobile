package com.example.cookbook.recipes_activity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Recipe(val id: String?, val name: String, val description: String, val ingredients: List<Ingredient>) : Parcelable