package com.oguzdogdu.recipes.presentation.listfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oguzdogdu.recipes.domain.model.RecipeResponse
import com.oguzdogdu.recipes.domain.repository.RecipeRepoInterface
import com.oguzdogdu.recipes.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val repo: RecipeRepoInterface) : ViewModel() {

    private val _response = MutableLiveData<Resource<RecipeResponse>>()
    val recipeResponse: LiveData<Resource<RecipeResponse>>
        get() = _response

    init {
        getRecipies()
    }

    private fun getRecipies() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repo.allRecipes()
            delay(1000)
            _response.postValue(result)

        }
    }
}