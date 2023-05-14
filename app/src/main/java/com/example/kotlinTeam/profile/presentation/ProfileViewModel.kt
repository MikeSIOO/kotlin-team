package com.example.kotlinTeam.profile.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.filter
import androidx.paging.map
import com.example.kotlinTeam.profile.common.Resource
import com.example.kotlinTeam.profile.domain.useCases.ProfileUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileUseCases: ProfileUseCases
) : ViewModel() {

    private val _stateProfile = MutableStateFlow(ProfileState())
    val stateProfile: StateFlow<ProfileState> = _stateProfile


    init {
        getProfile()
    }

    fun onEvent(event: ProfileFragmentEvents) {
        when (event) {
            is ProfileFragmentEvents.LoadMadeRecipes -> {
                getRecipes()
            }
            is ProfileFragmentEvents.LoadProfile -> {
                getProfile()
                getRecipes()
            }
            is ProfileFragmentEvents.LoadRecipe -> {
            }
            is ProfileFragmentEvents.LogOut -> {
                logOut()
            }
        }
    }

    private fun getProfile() {
        profileUseCases.getProfile().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _stateProfile.value = stateProfile.value.copy(
                        profile = result.data,
                        isLoading = false,
                        error = ""
                    )
                }
                is Resource.Error -> {
                    _stateProfile.value = stateProfile.value.copy(
                        isLoading = false,
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _stateProfile.value = stateProfile.value.copy(
                        isLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getRecipes() {
        val recipes = profileUseCases.getMadeRecipes().map { pagingData ->
            pagingData.filter { !(it.id.isNullOrBlank() || it.title.isNullOrBlank()) } .map { it.toMadeRecipe() }
        }.cachedIn(viewModelScope)
        _stateProfile.value = stateProfile.value.copy(
            madeRecipes = recipes
        )
    }

    private fun logOut() = viewModelScope.launch {
        profileUseCases.logOut()
    }
}
