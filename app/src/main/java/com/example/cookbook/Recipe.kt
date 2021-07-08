package com.example.cookbook

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Recipe(val name: String, val description: String, val ingredients: List<Ingredient>) : Parcelable