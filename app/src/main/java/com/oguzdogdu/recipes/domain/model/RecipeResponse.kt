package com.oguzdogdu.recipes.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RecipeResponse(
    val recipes: List<Recipe>
) : Parcelable