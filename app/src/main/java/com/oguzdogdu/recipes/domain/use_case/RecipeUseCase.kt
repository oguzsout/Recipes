package com.oguzdogdu.recipes.domain.use_case

import com.oguzdogdu.recipes.domain.model.Recipe
import com.oguzdogdu.recipes.domain.repository.RecipeRepoInterface
import com.oguzdogdu.recipes.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOError
import javax.inject.Inject

class RecipeUseCase @Inject constructor(private val repository: RecipeRepoInterface) {
    operator fun invoke(): Flow<Resource<List<Recipe>>> = flow {
        try {
            emit(Resource.Loading())
            val recipeList = repository.allRecipes()
            if (recipeList.recipes.isNotEmpty()) {
                emit(Resource.Success(recipeList.recipes))
            } else {
                emit(Resource.Error(message = "No recipe found"))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Error!"))
        } catch (e: IOError) {
            emit(Resource.Error(message = "Could not reach internet"))
        }
    }
}