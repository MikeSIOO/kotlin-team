package com.example.kotkin_team.feed.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotkin_team.feed.data.Recipe
import com.example.kotkin_team.feed.domain.FeedLoadingState
import com.example.kotkin_team.feed.domain.FeedRepositoryImpl
import com.example.kotkin_team.feed.domain.GetFeedUseCase
import kotlinx.coroutines.launch

class FeedViewModel : ViewModel() {

    private val _loadingState = MutableLiveData<FeedLoadingState>()
    val loadingState: LiveData<FeedLoadingState> = _loadingState

    private val _recipes = MutableLiveData<List<Recipe>>()
    val recipes: LiveData<List<Recipe>> = _recipes

    private val getFeedUseCase = GetFeedUseCase(FeedRepositoryImpl())

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
}
