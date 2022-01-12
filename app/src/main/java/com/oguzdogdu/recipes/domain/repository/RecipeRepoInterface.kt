package com.oguzdogdu.recipes.domain.repository

import com.oguzdogdu.recipes.data.model.RecipeDto
import com.oguzdogdu.recipes.util.Resource

interface RecipeRepoInterface {
    suspend fun allRecipes(): RecipeDto
}