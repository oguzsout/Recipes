package com.oguzdogdu.recipes.domain.repository

import com.oguzdogdu.recipes.domain.model.RecipeResponse
import com.oguzdogdu.recipes.util.Resource

interface RecipeRepoInterface {
    suspend fun allRecipes(): Resource<RecipeResponse>
}