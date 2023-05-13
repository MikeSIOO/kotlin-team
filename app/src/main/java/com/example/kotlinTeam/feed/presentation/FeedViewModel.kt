package com.example.kotlinTeam.feed.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.kotlinTeam.common.data.dataSource.model.recipe.RecipeOo
import com.example.kotlinTeam.feed.domain.useCase.FeedUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val useCases: FeedUseCases
) : ViewModel() {

    private val _feedRecipes: MutableStateFlow<PagingData<RecipeOo>> =
        MutableStateFlow(PagingData.empty())
    val feedRecipes: StateFlow<PagingData<RecipeOo>> = _feedRecipes

    private val _currentRecipeState: MutableStateFlow<CurrentRecipeState> = MutableStateFlow(
        CurrentRecipeState()
    )
    val currentRecipeState: StateFlow<CurrentRecipeState> = _currentRecipeState

    init {
        viewModelScope.launch {
            try {
                useCases.getFeedUseCase().cachedIn(viewModelScope).collect {
                    _feedRecipes.value = it
                }
            } catch (e: Exception) {
                _feedRecipes.value = PagingData.empty()
            }
        }
    }

    fun setCurrentRecipe(recipe: RecipeOo?) {
        _currentRecipeState.value = currentRecipeState.value.copy(currentRecipe = recipe, isLoadingRecipe = false)
    }

    fun getAndSetRecipeById(id: String) {
        _currentRecipeState.value = currentRecipeState.value.copy(isLoadingRecipe = true)
        viewModelScope.launch {
            val recipe = useCases.getRecipeById(id)
            setCurrentRecipe(recipe)
        }
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
        _currentRecipeState.value = currentRecipeState.value.copy(
            topPosition = currentRecipeState.value.topPosition + diff
        )
    }

    override fun onCleared() {
        super.onCleared()
        setCurrentRecipe(null)
    }
}
