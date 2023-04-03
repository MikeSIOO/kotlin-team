package com.example.kotkin_team.profile.data.repository

import android.app.Application
import com.example.kotkin_team.R
import com.example.kotkin_team.profile.common.Resource
import com.example.kotkin_team.profile.domain.model.Profile
import com.example.kotkin_team.profile.domain.repository.ProfileRepository
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

@Singleton
class ProfileRepositoryImpl @Inject constructor(
    private val appContext: Application
) : ProfileRepository {

    override fun getProfile(id: Int): Flow<Resource<Profile>> = flow {
        try {
            emit(Resource.Loading<Profile>())
            val profile = withContext(Dispatchers.IO) {
                Profile(
                    id = id,
                    name = "Susan",
                    secondName = "Helmhock",
                    image = "https://sun9-9.userapi.com/impf/wpKh_I1m4InpdEDsX31RH4Fh2eLb3j" +
                        "-Bo9iA4A/4VibiWNxgdg.jpg?size=604x453&quality" +
                        "=96&sign=b9428274e0de3250c73c22b04d9f9173&c" +
                        "_uniq_tag=LRvg-JVGoUInZ0oqC--Fg1GaVjy84CrtSmJ_NrV8n7M&type=album"
                )
            }
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
