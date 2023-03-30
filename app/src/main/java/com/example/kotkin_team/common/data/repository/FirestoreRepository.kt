package com.example.kotkin_team.common.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.kotkin_team.common.data.data_source.model.recipe.*
import com.example.kotkin_team.profile.common.Constants
import com.example.kotkin_team.common.data.data_source.FirestorePagingSource
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirestoreRepository @Inject constructor(
    private val firestore: FirebaseFirestore
) {

    private suspend fun RecipeDto.toRecipeOo(): RecipeOo {
        return RecipeOo(
            id = this.id,
            title = this.title,
            image = this.image,
            cookingMinutes = this.cookingMinutes,
            cuisines = if (this.cuisines == null) {
                emptyList()
            } else {
                buildList<CuisineDto?> {
                    for (cuisineRef in this@toRecipeOo.cuisines) {
                        add(cuisineRef.get().await().toObject(CuisineDto::class.java))
                    }
                }
            },
            diets = if (this.diets == null) {
                emptyList()
            } else {
                buildList<DietDto?> {
                    for (dietRef in this@toRecipeOo.diets) {
                        add(dietRef.get().await().toObject(DietDto::class.java))
                    }
                }
            },
            servings = this.servings,
            ingredients= if (this.ingredients == null) {
                emptyList()
            } else {
                buildList<IngredientDto?> {
                    for(ingredientRef in this@toRecipeOo.ingredients) {
                        add(ingredientRef.get().await().toObject(IngredientDto::class.java))
                    }
                }
            },
            instructions = this.instructions ?: emptyMap()
        )
    }

    fun getRecipes(id: Int): Flow<PagingData<RecipeOo>> {
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
                    it.toObject(RecipeDto::class.java)!!.toRecipeOo()
                }
            }
        ).flow
    }
}
