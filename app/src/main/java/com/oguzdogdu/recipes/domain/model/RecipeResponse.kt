package com.oguzdogdu.recipes.domain.model

import android.os.Parcelable

@kotlinx.parcelize.Parcelize
data class RecipeResponse(
    val recipes: List<Recipe>
) : Parcelable