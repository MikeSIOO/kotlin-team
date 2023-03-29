package com.example.kotkin_team.storage.presentation.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotkin_team.storage.common.StorageStatuses
import com.example.kotkin_team.storage.domain.events.StorageProductEvents
import com.example.kotkin_team.storage.domain.model.StorageProduct
import com.example.kotkin_team.storage.domain.state.StorageProductState
import com.example.kotkin_team.storage.domain.state.StorageSelectProductState
import com.example.kotkin_team.storage.domain.use_cases.StorageUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class StorageProductViewModel @Inject constructor(
    private val storageUseCases: StorageUseCases
) : ViewModel() {
    private val _storageProductState = MutableStateFlow(StorageProductState())
    val storageProductState: StateFlow<StorageProductState> = _storageProductState

    private val _storageSelectProductState = MutableStateFlow(StorageSelectProductState())
    val storageSelectProductState: StateFlow<StorageSelectProductState> = _storageSelectProductState

    fun onEvent(event: StorageProductEvents) {
        when (event) {
            is StorageProductEvents.LoadProduct -> {
                getProduct(event.parentId)
            }
            is StorageProductEvents.SelectProduct -> {
                selectProduct(event.storageProduct)
            }
        }
    }

    private fun getProduct(parentId: Int) {
        storageUseCases.storageGetProduct(parentId).onEach { result ->
            when (result) {
                is StorageStatuses.Success -> {
                    _storageProductState.value = storageProductState.value.copy(
                        storageProduct = result.data ?: emptyList(),
                        isLoading = false,
                        error = ""
                    )
                }
                is StorageStatuses.Error -> {
                    _storageProductState.value = storageProductState.value.copy(
                        isLoading = false,
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is StorageStatuses.Loading -> {
                    _storageProductState.value = storageProductState.value.copy(
                        isLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun selectProduct(storageProduct: StorageProduct) {
        storageUseCases.storageSelectProduct(storageProduct).onEach { result ->
            when (result) {
                is StorageStatuses.Success -> {
                    _storageSelectProductState.value = storageSelectProductState.value.copy(
                        storageProduct = result.data,
                        isLoading = false,
                        error = ""
                    )
                }
                is StorageStatuses.Error -> {
                    _storageSelectProductState.value = storageSelectProductState.value.copy(
                        isLoading = false,
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is StorageStatuses.Loading -> {
                    _storageSelectProductState.value = storageSelectProductState.value.copy(
                        isLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}