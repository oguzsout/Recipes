package com.oguzdogdu.recipes.presentation.listfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oguzdogdu.recipes.domain.use_case.RecipeUseCase
import com.oguzdogdu.recipes.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val recipeUseCase: RecipeUseCase) : ViewModel() {

    private val _recipeList = MutableStateFlow<RecipeState>(RecipeState())
    val recipeList: StateFlow<RecipeState> = _recipeList

    init {
        getRecipies()
    }

    private fun getRecipies() {
        recipeUseCase().onEach {
            when (it) {
                is Resource.Loading -> {
                    _recipeList.value = RecipeState(isLoading = true)
                }
                is Resource.Success -> {
                    _recipeList.value = RecipeState(data = it.data)
                }
                is Resource.Error -> {
                    _recipeList.value = RecipeState(error = it.message ?: "")
                }
            }
        }.launchIn(viewModelScope)
    }
}






