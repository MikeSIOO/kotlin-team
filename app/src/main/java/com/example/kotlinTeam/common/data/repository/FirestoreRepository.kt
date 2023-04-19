package com.example.kotlinTeam.common.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.kotlinTeam.common.data.dataSource.FirestorePagingSource
import com.example.kotlinTeam.common.data.dataSource.model.recipe.*
import com.example.kotlinTeam.profile.common.Constants
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await

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
            difficulty = this.difficulty,
            cuisines = if (this.cuisines == null) {
                emptyList()
            } else {
                buildList<CuisineDto> {
                    for (cuisineRef in this@toRecipeOo.cuisines) {
                        val cuisine = cuisineRef.get().await().toObject(CuisineDto::class.java)
                        add(cuisine ?: continue)
                    }
                }
            },
            diets = if (this.diets == null) {
                emptyList()
            } else {
                buildList<DietDto> {
                    for (dietRef in this@toRecipeOo.diets) {
                        val diet = dietRef.get().await().toObject(DietDto::class.java)
                        add(diet ?: continue)
                    }
                }
            },
            description = this.description,
            servings = this.servings,
            ingredients = if (this.ingredients == null) {
                emptyList()
            } else {
                buildList<IngredientOo> {
                    for ((amount, ingredientRef) in this@toRecipeOo.ingredients) {
                        val ingredient = ingredientRef.get().await()
                            .toObject(IngredientDto::class.java)
                        add(ingredient?.title?.let { IngredientOo(it, amount) } ?: continue)
                    }
                }
            },
            instructions = if (this.instructions != null) {
                buildList<StepOo> {
                    for (step in this@toRecipeOo.instructions) {
                        add(StepOo(step.key, step.value))
                    }
                }
            } else {
                emptyList()
            }
        )
    }

    fun getRecipes(id: String?): Flow<PagingData<RecipeOo>> {
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
