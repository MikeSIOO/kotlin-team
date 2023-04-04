package com.example.kotkin_team.profile.domain.use_cases

import com.example.kotkin_team.profile.common.Resource
import com.example.kotkin_team.profile.domain.model.Profile
import com.example.kotkin_team.profile.domain.repository.ProfileRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetProfile @Inject constructor(
    private val repository: ProfileRepository
) {

    operator fun invoke(id: Int): Flow<Resource<Profile>> {
        return repository.getProfile(id)
    }
}
