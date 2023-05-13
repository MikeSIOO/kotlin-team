package com.example.kotlinTeam.profile.domain.useCases

import com.example.kotlinTeam.profile.common.Resource
import com.example.kotlinTeam.profile.domain.model.Profile
import com.example.kotlinTeam.profile.domain.repository.ProfileRepository
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class GetProfile @Inject constructor(
    private val repository: ProfileRepository
) {

    suspend operator fun invoke(): Flow<Resource<Profile>> {
        return withContext(Dispatchers.IO) { repository.getProfile() }
    }
}
