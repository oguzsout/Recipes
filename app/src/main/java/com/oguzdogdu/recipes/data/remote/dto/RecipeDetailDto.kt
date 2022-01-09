package com.oguzdogdu.recipes.data.remote.dto

import com.oguzdogdu.recipes.domain.model.Recipe

data class RecipeDetailDto(
    val aggregateLikes: Int,
    val cheap: Boolean,
    val creditsText: String,
    val dairyFree: Boolean,
    val gaps: String,
    val glutenFree: Boolean,
    val healthScore: Double,
    val id: Int,
    val image: String,
    val imageType: String,
    val instructions: String,
    val license: String,
    val lowFodmap: Boolean,
    val pricePerServing: Double,
    val readyInMinutes: Int,
    val servings: Int,
    val sourceName: String,
    val sourceUrl: String,
    val spoonacularScore: Double,
    val spoonacularSourceUrl: String,
    val summary: String,
    val sustainable: Boolean,
    val title: String,
    val vegan: Boolean,
    val vegetarian: Boolean,
    val veryHealthy: Boolean,
    val veryPopular: Boolean,
    val weightWatcherSmartPoints: Int
)

fun RecipeDetailDto.toRecipe(): Recipe {
    return Recipe(
        image,
        id,
        image,
        instructions,
        sourceUrl,
        summary,
        title,
        vegan,
        vegetarian,
        veryHealthy
    )
}


