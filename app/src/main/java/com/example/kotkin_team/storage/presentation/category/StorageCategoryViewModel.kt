package com.example.kotkin_team.storage.presentation.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotkin_team.storage.common.StorageStatuses
import com.example.kotkin_team.storage.domain.events.StorageCategoryEvents
import com.example.kotkin_team.storage.domain.state.StorageCategoryState
import com.example.kotkin_team.storage.domain.use_cases.StorageUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class StorageCategoryViewModel @Inject constructor(
    private val storageUseCases: StorageUseCases
) : ViewModel() {
    private val _storageCategoryState = MutableStateFlow(StorageCategoryState())
    val storageCategoryState: StateFlow<StorageCategoryState> = _storageCategoryState

    fun onEvent(event: StorageCategoryEvents) {
        when (event) {
            is StorageCategoryEvents.LoadCategory -> {
                getCategory()
            }
        }
    }

    private fun getCategory() {
        storageUseCases.storageGetCategory().onEach { result ->
            when (result) {
                is StorageStatuses.Success -> {
                    _storageCategoryState.value = storageCategoryState.value.copy(
                        storageCategory = result.data ?: emptyList(),
                        isLoading = false,
                        error = ""
                    )
                }
                is StorageStatuses.Error -> {
                    _storageCategoryState.value = storageCategoryState.value.copy(
                        isLoading = false,
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is StorageStatuses.Loading -> {
                    _storageCategoryState.value = storageCategoryState.value.copy(
                        isLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}