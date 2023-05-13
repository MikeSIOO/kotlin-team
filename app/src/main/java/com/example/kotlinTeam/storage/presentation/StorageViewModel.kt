package com.example.kotlinTeam.storage.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.kotlinTeam.storage.common.StorageStatuses
import com.example.kotlinTeam.storage.domain.events.StorageEvents
import com.example.kotlinTeam.storage.domain.model.StorageDataModel
import com.example.kotlinTeam.storage.domain.state.StorageSelectProductState
import com.example.kotlinTeam.storage.domain.state.StorageState
import com.example.kotlinTeam.storage.domain.useCases.StorageGetCategoryUseCase
import com.example.kotlinTeam.storage.domain.useCases.StorageGetProductUseCase
import com.example.kotlinTeam.storage.domain.useCases.StorageSelectProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@HiltViewModel
class StorageViewModel @Inject constructor(
    private val storageGetCategoryUseCase: StorageGetCategoryUseCase,
    private val storageGetProductUseCase: StorageGetProductUseCase,
    private val storageSelectProductUseCase: StorageSelectProductUseCase
) : ViewModel() {
    private val _storageState: MutableStateFlow<StorageState> = MutableStateFlow(
        StorageState(isLoading = true, storageData = null)
    )
    val storageState: StateFlow<StorageState> = _storageState

    private val _storageSelectProductState = MutableStateFlow(StorageSelectProductState())
    val storageSelectProductState: StateFlow<StorageSelectProductState> = _storageSelectProductState


    init {
        getCategory()
    }

    fun onEvent(event: StorageEvents) {
        when (event) {
            is StorageEvents.InitCategory -> {
                getCategory()
            }
            is StorageEvents.InitProduct -> {
                getProduct(event.parentId)
            }
            is StorageEvents.SelectProduct -> {
                selectProduct(event.storageProduct)
            }
        }
    }

    private fun getCategory() {
        viewModelScope.launch {
            _storageState.value = StorageState(isLoading = true)
            try {
                storageGetCategoryUseCase.invoke().cachedIn(viewModelScope).collect {
                    _storageState.value = StorageState(
                        isLoading = false,
                        storageData = it
                    )
                }
            } catch (e: Exception) {
                _storageState.value = StorageState(
                    isLoading = false,
                    error = e.localizedMessage ?: "Unknown error",
                )
            }
        }
    }

    private fun getProduct(parentId: String) {
        viewModelScope.launch {
            _storageState.value = StorageState(isLoading = true)
            try {
                storageGetProductUseCase.invoke(parentId).cachedIn(viewModelScope).collect {
                    _storageState.value = StorageState(
                        isLoading = false,
                        storageData = it
                    )
                }
            } catch (e: Exception) {
                _storageState.value = StorageState(
                    isLoading = false,
                    error = e.localizedMessage ?: "Unknown error",
                )
            }
        }
    }

    private fun selectProduct(storageProduct: StorageDataModel.StorageProduct) {
        storageSelectProductUseCase(storageProduct).onEach { result ->
            _storageSelectProductState.value = when (result) {
                is StorageStatuses.Success -> {
                    storageSelectProductState.value.copy(
                        storageProduct = result.data,
                        isLoading = false,
                        error = ""
                    )
                }
                is StorageStatuses.Error -> {
                    storageSelectProductState.value.copy(
                        isLoading = false,
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is StorageStatuses.Loading -> {
                    storageSelectProductState.value.copy(
                        isLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}
