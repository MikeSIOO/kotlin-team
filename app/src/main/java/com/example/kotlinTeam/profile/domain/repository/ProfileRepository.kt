package com.example.kotlinTeam.profile.domain.repository

import com.example.kotlinTeam.profile.common.Resource
import com.example.kotlinTeam.profile.domain.model.Profile
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {

    fun getProfile(): Flow<Resource<Profile>>
}
