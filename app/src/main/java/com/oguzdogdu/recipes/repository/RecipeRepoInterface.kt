package com.oguzdogdu.recipes.repository

import com.oguzdogdu.recipes.model.RecipeResponse
import com.oguzdogdu.recipes.util.Resource

interface RecipeRepoInterface {
    suspend fun allRecipes(): Resource<RecipeResponse>
}