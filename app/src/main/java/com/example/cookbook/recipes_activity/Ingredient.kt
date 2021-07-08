package com.example.cookbook.recipes_activity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Ingredient(val name: String) : Parcelable