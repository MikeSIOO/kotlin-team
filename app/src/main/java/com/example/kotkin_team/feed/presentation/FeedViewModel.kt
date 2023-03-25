package com.example.kotkin_team.feed.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotkin_team.feed.data.Recipe
import com.example.kotkin_team.feed.domain.FeedLoadingState
import com.example.kotkin_team.feed.domain.GetFeedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val getFeedUseCase: GetFeedUseCase
) : ViewModel() {

    private val _loadingState = MutableLiveData<FeedLoadingState>()
    val loadingState: LiveData<FeedLoadingState> = _loadingState

    private val _recipes = MutableLiveData<List<Recipe>>()
    val recipes: LiveData<List<Recipe>> = _recipes

    private val _currentRecipe = MutableLiveData<Recipe?>()
    val currentRecipe: LiveData<Recipe?> = _currentRecipe

    init {
        viewModelScope.launch {
            try {
                _loadingState.value = FeedLoadingState.LOADING
                _recipes.value = getFeedUseCase.getRecipes()
                _loadingState.value = FeedLoadingState.LOADED
            } catch (e: Exception) {
                _loadingState.value = FeedLoadingState.error(e.message)
                _recipes.value = emptyList()
            }
        }
    }

    fun setCurrentRecipe(recipe: Recipe?) {
        _currentRecipe.value = recipe
    }
}
