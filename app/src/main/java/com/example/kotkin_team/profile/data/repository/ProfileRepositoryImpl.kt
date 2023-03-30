package com.example.kotkin_team.profile.data.repository

import android.app.Application
import com.example.kotkin_team.R
import com.example.kotkin_team.profile.common.Resource
import com.example.kotkin_team.profile.domain.model.Profile
import com.example.kotkin_team.profile.domain.repository.ProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileRepositoryImpl @Inject constructor(
    private val appContext: Application
) : ProfileRepository {

    override fun getProfile(id: Int): Flow<Resource<Profile>> = flow {
        try {
            emit(Resource.Loading<Profile>())
            val profile = withContext(Dispatchers.IO) {
                delay(500L)
                Profile(
                    id = id,
                    name = "Susan",
                    secondName = "Helmhock"
                )
            }
            emit(Resource.Success<Profile>(profile))
        } catch (e: IOException) {
            emit(Resource.Error<Profile>(appContext.getString(R.string.error_no_internet_connection)))
        }
    }
}