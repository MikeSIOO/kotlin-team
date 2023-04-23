package com.example.kotlinTeam.feed.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.kotlinTeam.common.data.dataSource.model.recipe.RecipeOo
import com.example.kotlinTeam.feed.domain.useCase.FeedUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val useCases: FeedUseCases
) : ViewModel() {

    private val _feedState: MutableStateFlow<FeedState> =
        MutableStateFlow(FeedState(isLoading = true, data = null))
    val feedState: StateFlow<FeedState> = _feedState.asStateFlow()

    private val _currentRecipeState: MutableStateFlow<CurrentRecipeState> = MutableStateFlow(
        CurrentRecipeState()
    )
    val currentRecipeState: StateFlow<CurrentRecipeState> = _currentRecipeState

    init {
        System.out.println("state 0 vm ${_feedState.value}")
        viewModelScope.launch {

            _feedState.value = FeedState(isLoading = true, topPosition = 0)
            try {
                useCases.getFeedUseCase().cachedIn(viewModelScope).collect {
                    _feedState.value = FeedState(isLoading = false, data = it, topPosition = 0)
                }
            } catch (e: Exception) {
                _feedState.value = FeedState(
                    isLoading = false,
                    error = e.localizedMessage ?: "Unknown error",
                    topPosition = 0
                )
            }
        }
    }

    fun setCurrentRecipe(recipe: RecipeOo?) {
        _currentRecipeState.value = currentRecipeState.value.copy(currentRecipe = recipe)
    }

    fun changeFlag() {
        _currentRecipeState.value =
            currentRecipeState.value.copy(
                isMoreInfoButtonClicked = !currentRecipeState.value.isMoreInfoButtonClicked
            )
    }

    fun resetFlag() {
        _currentRecipeState.value =
            currentRecipeState.value.copy(
                isMoreInfoButtonClicked = false
            )
    }

    fun changeManagerTopPosition(diff: Int) {
        _feedState.value =
            feedState.value.copy(
                topPosition = feedState.value.topPosition + diff
            )
    }

    override fun onCleared() {
        super.onCleared()
        setCurrentRecipe(null)
    }
}
