package com.oguzdogdu.recipes.domain.model

import android.os.Parcelable

@kotlinx.parcelize.Parcelize
data class Recipe(
    val creditsText: String,
    val id: Int,
    val image: String,
    val instructions: String,
    val sourceUrl: String,
    val summary: String,
    val title: String,
    val spoonacularSourceUrl: String,
    val vegan: Boolean,
    val vegetarian: Boolean,
    val veryHealthy: Boolean,

) : Parcelable