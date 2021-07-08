package com.example.cookbook

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Ingredient(val name: String) : Parcelable