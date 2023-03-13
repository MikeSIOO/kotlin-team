package com.example.kotkin_team.profile.data.repository

import com.example.kotkin_team.profile.common.Resource
import com.example.kotkin_team.profile.data.data_source.remote.FakeApi
import com.example.kotkin_team.profile.domain.model.Recipe
import com.example.kotkin_team.profile.domain.model.Profile
import com.example.kotkin_team.profile.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ProfileRepositoryImpl @Inject constructor(
    private val fakeApi: FakeApi
): ProfileRepository{

    override fun getProfile(id: Int): Flow<Resource<Profile>> = flow {

        try {
            emit(Resource.Loading<Profile>())
            val recipes = fakeApi.getProfile(id)
            emit(Resource.Success<Profile>(recipes))
        } catch (e: IOException) {
            emit(Resource.Error<Profile>("Не обнаружено соединение с сервером. Проверьте интернет подключение"))
        }
    }

    override fun getMadeRecipes(profile: Profile): Flow<Resource<List<Recipe>>> = flow {

        try {
            emit(Resource.Loading<List<Recipe>>())
            val recipes = fakeApi.getMadeRecipes(profile).recipes.map { it.toRecipe() }
            emit(Resource.Success<List<Recipe>>(recipes))
        } catch (e: IOException) {
            emit(Resource.Error<List<Recipe>>("Не обнаружено соединение с сервером. Проверьте интернет подключение"))
        }
    }
}