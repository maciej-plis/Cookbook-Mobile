package com.example.cookbook.recipes_activity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Ingredient(val name: String) : Parcelable