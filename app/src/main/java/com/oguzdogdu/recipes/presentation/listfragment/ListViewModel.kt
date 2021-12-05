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
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val repo: RecipeRepoInterface) : ViewModel() {

    private val _response = MutableLiveData<Resource<RecipeResponse>>()
    val newsResponse: LiveData<Resource<RecipeResponse>>
        get() = _response


    fun getRecipies() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repo.allRecipes()
            _response.postValue(result)
        }
    }
}