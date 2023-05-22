package com.example.kotlinTeam.feed.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.kotlinTeam.common.data.dataSource.model.recipe.RecipeOo
import com.example.kotlinTeam.feed.domain.useCase.FeedUseCases
import com.example.kotlinTeam.storage.common.StorageStatuses
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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

    private val _selectedProductsState: MutableStateFlow<SelectedProductsState> = MutableStateFlow(
        SelectedProductsState()
    )
    private val selectedProductsState: StateFlow<SelectedProductsState> = _selectedProductsState

    private val _feedEndState: MutableStateFlow<FeedEndState> = MutableStateFlow(
        FeedEndState()
    )
    val feedEndState: StateFlow<FeedEndState> = _feedEndState

    init {
        viewModelScope.launch {
            useCases.getSelectedProductsUseCase().collect { result ->
                setManagerTopPosition(0)
                _selectedProductsState.value =
                    when (result) {
                        is StorageStatuses.Success -> {
                            selectedProductsState.value.copy(
                                isLoading = false,
                                selectedProducts = result.data ?: emptyList()
                            )
                        }

                        is StorageStatuses.Error -> {
                            selectedProductsState.value.copy(
                                isLoading = false,
                                selectedProducts = emptyList(),
                                error = result.message
                            )
                        }

                        is StorageStatuses.Loading -> {
                            selectedProductsState.value.copy(
                                isLoading = true,
                                selectedProducts = emptyList()
                            )
                        }
                    }
                getRecipes()
            }
        }
    }

    private fun getRecipes() {
        setAllRecipesWereShown(false)
        viewModelScope.launch {
            selectedProductsState.value.let {
                if (!it.isLoading) {
                    try {
                        useCases.getFeedUseCase(selectedProductsState.value.selectedProducts)
                            .cachedIn(viewModelScope).collect { recipes ->
                                _feedRecipes.value = recipes
                            }
                    } catch (e: Exception) {
                        _feedRecipes.value = PagingData.empty()
                    }
                }
            }
        }
    }

    fun getAllRecipes() {
        viewModelScope.launch {
            try {
                useCases.getFeedUseCase().cachedIn(viewModelScope).collect { recipes ->
                    _feedRecipes.value = recipes
                }
            } catch (e: Exception) {
                _feedRecipes.value = PagingData.empty()
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
        _currentRecipeState.value = currentRecipeState.value.copy(
            topPosition = currentRecipeState.value.topPosition + diff
        )
    }

    fun setManagerTopPosition(newPosition: Int) {
        _currentRecipeState.value = currentRecipeState.value.copy(
            topPosition = newPosition
        )
    }

    fun setEndOfFeed(flag: Boolean) {
        _feedEndState.value = feedEndState.value.copy(
            isEndOfFeed = flag
        )
    }

    fun setAllRecipesWereShown(flag: Boolean) {
        _feedEndState.value = feedEndState.value.copy(
            allRecipesWereShown = flag
        )
    }

    fun setRecipesWereFound(flag: Boolean) {
        _feedEndState.value = feedEndState.value.copy(
            recipesWereFound = flag
        )
    }

    override fun onCleared() {
        super.onCleared()
        setCurrentRecipe(null)
    }
}
