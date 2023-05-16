package com.example.kotlinTeam.profile.domain.useCases

import com.example.kotlinTeam.profile.common.Resource
import com.example.kotlinTeam.profile.domain.model.Profile
import com.example.kotlinTeam.profile.domain.repository.ProfileRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetProfile @Inject constructor(
    private val repository: ProfileRepository
) {

    operator fun invoke(): Flow<Resource<Profile>> {
        return repository.getProfile()
    }
}
