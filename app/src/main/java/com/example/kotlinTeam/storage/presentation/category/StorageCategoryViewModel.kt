package com.example.kotlinTeam.storage.presentation.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinTeam.storage.common.StorageStatuses
import com.example.kotlinTeam.storage.domain.events.StorageCategoryEvents
import com.example.kotlinTeam.storage.domain.state.StorageCategoryState
import com.example.kotlinTeam.storage.domain.useCases.StorageGetCategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@HiltViewModel
class StorageCategoryViewModel @Inject constructor(
    private val storageGetCategoryUseCase: StorageGetCategoryUseCase
) : ViewModel() {
    private val _storageCategoryState = MutableStateFlow(StorageCategoryState())
    val storageCategoryState: StateFlow<StorageCategoryState> = _storageCategoryState

    init {
        getCategory()
    }

    fun onEvent(event: StorageCategoryEvents) {
        when (event) {
            is StorageCategoryEvents.InitCategory -> {
                getCategory()
            }
        }
    }

    private fun getCategory() {
        storageGetCategoryUseCase().onEach { result ->
            _storageCategoryState.value = when (result) {
                is StorageStatuses.Success -> {
                    storageCategoryState.value.copy(
                        storageCategory = result.data ?: emptyList(),
                        isLoading = false,
                        error = ""
                    )
                }

                is StorageStatuses.Error -> {
                    storageCategoryState.value.copy(
                        isLoading = false,
                        error = result.message ?: "An unexpected error occurred"
                    )
                }

                is StorageStatuses.Loading -> {
                    storageCategoryState.value.copy(
                        isLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}
