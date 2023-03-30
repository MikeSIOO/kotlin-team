package com.example.kotkin_team.profile.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.kotkin_team.common.data.repository.FirestoreRepository
import com.example.kotkin_team.profile.common.Resource
import com.example.kotkin_team.profile.domain.model.MadeRecipe
import com.example.kotkin_team.profile.domain.model.Profile
import com.example.kotkin_team.profile.domain.use_cases.ProfileUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileUseCases: ProfileUseCases
) : ViewModel() {

    private val profileId = 1

    private val _stateProfile = MutableStateFlow(ProfileState())
    val stateProfile: StateFlow<ProfileState> = _stateProfile

    private val _madeRecipes: Flow<PagingData<MadeRecipe>> = getRecipes()
    val madeRecipes: Flow<PagingData<MadeRecipe>> = _madeRecipes

    init {
        getProfile(profileId)
    }

    fun onEvent(event: ProfileFragmentEvents) {
        when (event) {
            is ProfileFragmentEvents.LoadMadeRecipes -> {
                getRecipes()
            }
            is ProfileFragmentEvents.LoadProfile -> {
                getProfile(profileId)
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
                        profile = result.data ?: Profile(
                            id = -1,
                            name = "not found",
                            secondName = null,
                            image = ""
                        ),
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

    private fun getRecipes() = profileUseCases.getMadeRecipes(profileId).map { pagingData ->
        pagingData.map { it.toMadeRecipe() }
    }.cachedIn(viewModelScope)
}
