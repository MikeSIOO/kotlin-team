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

    private val _feedState = MutableLiveData<FeedState>()
    val feedState: LiveData<FeedState> = _feedState

    init {
        _feedState.value = FeedState()
        viewModelScope.launch {
            try {
                _feedState.value = feedState.value?.copy(loadingState = FeedLoadingState.LOADING)
                _feedState.value = feedState.value?.copy(recipeList = getFeedUseCase.getRecipes())
                _feedState.value = feedState.value?.copy(loadingState = FeedLoadingState.LOADED)
            } catch (e: Exception) {
                _feedState.value = feedState.value?.copy(loadingState = FeedLoadingState.error(e.message), recipeList = emptyList() )
            }
        }
    }

    fun setCurrentRecipe(recipe: Recipe?) {
        _feedState.value = feedState.value?.copy(currentRecipe = recipe)
    }

    fun setFlag() {
        _feedState.value = feedState.value?.copy(isMoreInfoButtonClicked = !feedState.value?.isMoreInfoButtonClicked!!)
    }

    override fun onCleared() {
        super.onCleared()
        setCurrentRecipe(null)
    }
}
