package com.example.kotkin_team.common.data.repository

import android.app.Application
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.kotkin_team.R
import com.example.kotkin_team.profile.common.Constants
import com.example.kotkin_team.profile.common.Resource
import com.example.kotkin_team.common.data.data_source.model.recipe.RecipeDto
import com.example.kotkin_team.common.data.data_source.remote.FirestorePagingSource
import com.example.kotkin_team.profile.domain.model.Profile
import com.example.kotkin_team.profile.domain.repository.ProfileRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

class FirestoreRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
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

    override fun getRecipes(id: Int): Flow<PagingData<RecipeDto>> {
        val recipeCollectionRef = firestore.collection(Constants.RECIPES_COLLECTION)
        val query = recipeCollectionRef.orderBy(Constants.TITLE_PROPERTY)

        val pagingConfig = PagingConfig(
            pageSize = Constants.PAGE_SIZE,
            enablePlaceholders = false,
            initialLoadSize = Constants.INITIAL_LOAD_SIZE
        )

        return Pager(
            config = pagingConfig,
            pagingSourceFactory = {
                FirestorePagingSource(query) {
                    it.toObject(RecipeDto::class.java)!!
                }
            }
        ).flow
    }
}
