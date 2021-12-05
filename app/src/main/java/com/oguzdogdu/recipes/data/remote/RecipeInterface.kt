package com.oguzdogdu.recipes.data.remote

import com.oguzdogdu.recipes.domain.model.RecipeResponse
import com.oguzdogdu.recipes.util.Constants.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeInterface {

    @GET("recipes/random")
    suspend fun getRandomRecipes(
        @Query("apiKey") apiKey: String = API_KEY,
        @Query("number") number : Int = 20
    ): Response<RecipeResponse>
}