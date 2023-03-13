package com.example.kotkin_team.profile.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotkin_team.profile.common.Resource
import com.example.kotkin_team.profile.domain.model.Profile
import com.example.kotkin_team.profile.domain.use_cases.ProfileUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileUseCases: ProfileUseCases
) : ViewModel() {

    private val _stateMadeRecipes = MutableStateFlow(MadeRecipesListState())
    val stateMadeRecipes: StateFlow<MadeRecipesListState> = _stateMadeRecipes

    private val _stateProfile = MutableStateFlow(ProfileState())
    val stateProfile: StateFlow<ProfileState> = _stateProfile

    fun onEvent(event: ProfileFragmentEvents) {
        when (event) {
            is ProfileFragmentEvents.LoadMadeRecipes -> {
                getMadeRecipes(event.profile)
            }
            is ProfileFragmentEvents.LoadProfile -> {
                getProfile(event.id)
            }
            is ProfileFragmentEvents.LoadRecipe -> {

            }
        }
    }

    private fun getProfile(id: Int) {
        profileUseCases.getProfile(id).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _stateProfile.value = stateProfile.value.copy(
                        profile = result.data ?: Profile(id = -1, name = "not found", secondName = null, image = null),
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

    private fun getMadeRecipes(profile: Profile) {
        profileUseCases.getMadeRecipes(profile).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _stateMadeRecipes.value = stateMadeRecipes.value.copy(
                        madeRecipes = result.data ?: emptyList(),
                        isLoading = false,
                        error = ""
                    )
                }
                is Resource.Error -> {
                    _stateMadeRecipes.value = stateMadeRecipes.value.copy(
                        isLoading = false,
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _stateMadeRecipes.value = stateMadeRecipes.value.copy(
                        isLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}