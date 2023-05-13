package com.example.kotlinTeam.storage.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.kotlinTeam.storage.domain.events.StorageCategoryEvents
import com.example.kotlinTeam.storage.domain.events.StorageProductEvents
import com.example.kotlinTeam.storage.domain.state.StorageCategoryState
import com.example.kotlinTeam.storage.domain.state.StorageProductState
import com.example.kotlinTeam.storage.domain.state.StorageSelectProductState
import com.example.kotlinTeam.storage.domain.useCases.StorageGetCategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class StorageViewModel @Inject constructor(
    private val storageGetCategoryUseCase: StorageGetCategoryUseCase
) : ViewModel() {
    private val _storageCategoryState: MutableStateFlow<StorageCategoryState> = MutableStateFlow(
        StorageCategoryState(isLoading = true, storageCategory = null)
    )
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
        viewModelScope.launch {
            _storageCategoryState.value = StorageCategoryState(isLoading = true)
            try {
                storageGetCategoryUseCase.invoke().cachedIn(viewModelScope).collect {
                    _storageCategoryState.value = StorageCategoryState(
                        isLoading = false,
                        storageCategory = it
                    )
                }
            } catch (e: Exception) {
                _storageCategoryState.value = StorageCategoryState(
                    isLoading = false,
                    error = e.localizedMessage ?: "Unknown error",
                )
            }
        }
    }
}
