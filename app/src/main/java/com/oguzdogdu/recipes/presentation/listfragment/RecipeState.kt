package com.oguzdogdu.recipes.presentation.listfragment

import com.oguzdogdu.recipes.domain.model.Recipe

data class RecipeState(
    val isLoading : Boolean = false,
    val data: List<Recipe>? = null,
    val error : String = ""
)
