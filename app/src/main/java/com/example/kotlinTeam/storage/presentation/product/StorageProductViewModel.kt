package com.example.kotlinTeam.storage.presentation.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.kotlinTeam.storage.common.StorageStatuses
import com.example.kotlinTeam.storage.domain.events.StorageProductEvents
import com.example.kotlinTeam.storage.domain.model.StorageProduct
import com.example.kotlinTeam.storage.domain.state.StorageProductState
import com.example.kotlinTeam.storage.domain.state.StorageSelectProductState
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
class StorageProductViewModel @Inject constructor(
    private val storageGetProductUseCase: StorageGetProductUseCase,
    private val storageSelectProductUseCase: StorageSelectProductUseCase
) : ViewModel() {
    private val _parentId = MutableLiveData("")
    val parentId: LiveData<String> = _parentId


    private val _storageProductState: MutableStateFlow<StorageProductState> = MutableStateFlow(
        StorageProductState(isLoading = true, storageProduct = null)
    )
    val storageProductState: StateFlow<StorageProductState> = _storageProductState

    private val _storageSelectProductState = MutableStateFlow(StorageSelectProductState())
    val storageSelectProductState: StateFlow<StorageSelectProductState> = _storageSelectProductState

    fun onEvent(event: StorageProductEvents) {
        when (event) {
            is StorageProductEvents.InitProduct -> {
                _parentId.value = event.parentId
            }
            is StorageProductEvents.LoadProduct -> {
                getProduct()
            }
            is StorageProductEvents.SelectProduct -> {
                selectProduct(event.storageProduct)
            }
        }
    }

    private fun getProduct() {
        viewModelScope.launch {
            _storageProductState.value = StorageProductState(isLoading = true)
            try {
                storageGetProductUseCase.invoke(parentId.value!!).cachedIn(viewModelScope).collect {
                    _storageProductState.value = StorageProductState(isLoading = false, storageProduct = it)
                }
            } catch (e: Exception) {
                _storageProductState.value = StorageProductState(
                    isLoading = false,
                    error = e.localizedMessage ?: "Unknown error",
                )
            }
        }
    }

    private fun selectProduct(storageProduct: StorageProduct) {
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
