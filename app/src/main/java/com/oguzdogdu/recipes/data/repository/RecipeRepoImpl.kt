package com.oguzdogdu.recipes.data.repository

import com.oguzdogdu.recipes.data.model.RecipeDto
import com.oguzdogdu.recipes.data.remote.RecipeInterface
import com.oguzdogdu.recipes.domain.repository.RecipeRepoInterface
import com.oguzdogdu.recipes.util.Constants.API_KEY
import com.oguzdogdu.recipes.util.Constants.PAGE_NUM
import javax.inject.Inject

class RecipeRepoImpl @Inject constructor(private val apiService: RecipeInterface) :
    RecipeRepoInterface {
    override suspend fun allRecipes(): RecipeDto {
        return apiService.getRandomRecipes(API_KEY, PAGE_NUM)
    }
}