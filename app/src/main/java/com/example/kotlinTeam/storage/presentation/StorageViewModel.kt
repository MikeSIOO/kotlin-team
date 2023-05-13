package com.example.kotlinTeam.storage.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.kotlinTeam.storage.domain.events.StorageEvents
import com.example.kotlinTeam.storage.domain.state.StorageState
import com.example.kotlinTeam.storage.domain.useCases.StorageGetCategoryUseCase
import com.example.kotlinTeam.storage.domain.useCases.StorageGetProductUseCase
import com.example.kotlinTeam.storage.domain.useCases.StorageSelectProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class StorageViewModel @Inject constructor(
    private val storageGetCategoryUseCase: StorageGetCategoryUseCase,
    private val storageGetProductUseCase: StorageGetProductUseCase,
    private val storageSelectProductUseCase: StorageSelectProductUseCase
) : ViewModel() {
    private val _storageCategoryState: MutableStateFlow<StorageState> = MutableStateFlow(
        StorageState(isLoading = true, storageData = null)
    )
    val storageCategoryState: StateFlow<StorageState> = _storageCategoryState

    init {
        getCategory()
    }

    fun onEvent(event: StorageEvents) {
        when (event) {
            is StorageEvents.InitCategory -> {
                getCategory()
            }
            else -> {}
        }
    }

    private fun getCategory() {
        viewModelScope.launch {
            _storageCategoryState.value = StorageState(isLoading = true)
            try {
//                storageGetCategoryUseCase.invoke("JTKAzyPCwNRQLMxDdjKY").cachedIn(viewModelScope).collect {
                storageGetProductUseCase.invoke("JTKAzyPCwNRQLMxDdjKY").cachedIn(viewModelScope).collect {
                    _storageCategoryState.value = StorageState(
                        isLoading = false,
                        storageData = it
                    )
                }
            } catch (e: Exception) {
                _storageCategoryState.value = StorageState(
                    isLoading = false,
                    error = e.localizedMessage ?: "Unknown error",
                )
            }
        }
    }
}
