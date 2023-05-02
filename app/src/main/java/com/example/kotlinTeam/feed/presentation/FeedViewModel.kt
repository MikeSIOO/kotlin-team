package com.example.kotlinTeam.feed.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.kotlinTeam.common.data.dataSource.model.recipe.RecipeOo
import com.example.kotlinTeam.feed.domain.useCase.FeedUseCases
import com.example.kotlinTeam.storage.common.StorageStatuses
import com.example.kotlinTeam.storage.domain.state.StorageProductState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject
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

    private val _selectedProductsState = MutableStateFlow(StorageProductState())
    val selectedProductsState: StateFlow<StorageProductState> = _selectedProductsState

    init {
        getSelectedProducts()
        viewModelScope.launch {
            try {
                getSelectedProducts()
                useCases.getFeedUseCase().cachedIn(viewModelScope).collect {
                    _feedRecipes.value = it
                }
            } catch (e: Exception) {
                _feedRecipes.value = PagingData.empty()
            }
        }
    }

    private fun getSelectedProducts () {
        useCases.getSelectedProductsUseCase().onEach { result ->
            _selectedProductsState.value = when (result) {
                is StorageStatuses.Success -> {
                    selectedProductsState.value.copy(
                        storageProduct = result.data ?: emptyList(),
                        isLoading = false,
                        error = ""
                    )
                }
                is StorageStatuses.Error -> {
                    selectedProductsState.value.copy(
                        isLoading = false,
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is StorageStatuses.Loading -> {
                    selectedProductsState.value.copy(
                        isLoading = true
                    )
                }
            }

        }.launchIn(viewModelScope)
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

    override fun onCleared() {
        super.onCleared()
        setCurrentRecipe(null)
    }
}
