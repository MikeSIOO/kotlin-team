package com.example.kotlinTeam.profile.data.repository

import android.content.Context
import com.example.kotlinTeam.R
import com.example.kotlinTeam.common.data.repository.AuthRepository
import com.example.kotlinTeam.profile.common.Resource
import com.example.kotlinTeam.profile.domain.model.Profile
import com.example.kotlinTeam.profile.domain.repository.ProfileRepository
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

@Singleton
class ProfileRepositoryImpl @Inject constructor(
    private val authRepository: AuthRepository,
    private val appContext: Context
) : ProfileRepository {

    override fun getProfile(): Flow<Resource<Profile>> = flow {
        try {
            emit(Resource.Loading<Profile>())
            val user = withContext(Dispatchers.IO) { authRepository.getCurrentUser() }
            val profile = user?.let {
                Profile(
                    id = it.uid,
                    name = it.displayName ?: "нет имени",
                    image = (it.photoUrl ?: "") as String
                )
            } ?: throw IOException("Пользователь не найден")
            emit(Resource.Success<Profile>(profile))
        } catch (e: IOException) {
            emit(
                Resource.Error<Profile>(
                    appContext.getString(R.string.error_no_internet_connection)
                )
            )
        }
    }
}
