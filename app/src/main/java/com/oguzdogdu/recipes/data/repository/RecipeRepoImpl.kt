package com.oguzdogdu.recipes.data.repository

import com.oguzdogdu.recipes.data.remote.RecipeInterface
import com.oguzdogdu.recipes.domain.model.RecipeResponse
import com.oguzdogdu.recipes.domain.repository.RecipeRepoInterface
import com.oguzdogdu.recipes.util.Constants.API_KEY
import com.oguzdogdu.recipes.util.Resource
import javax.inject.Inject

class RecipeRepoImpl @Inject constructor(private val apiService: RecipeInterface) :
    RecipeRepoInterface {
    override suspend fun allRecipes(): Resource<RecipeResponse> {
        return try {
            val response = apiService.getRandomRecipes(API_KEY)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("Error", null)
            } else {
                Resource.error("Error", null)
            }
        } catch (e: Exception) {
            Resource.error("No data!", null)
        }
    }
}