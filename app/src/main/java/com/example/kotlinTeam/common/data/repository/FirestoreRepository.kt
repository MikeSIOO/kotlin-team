package com.example.kotlinTeam.common.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.kotlinTeam.common.data.dataSource.FirestorePagingSource
import com.example.kotlinTeam.common.data.dataSource.model.recipe.*
import com.example.kotlinTeam.common.data.dataSource.model.storage.StorageCategoryDto
import com.example.kotlinTeam.common.data.dataSource.model.storage.StorageProductDto
import com.example.kotlinTeam.profile.common.Constants
import com.example.kotlinTeam.storage.data.db.service.StorageProductDao
import com.example.kotlinTeam.storage.data.mapper.StorageMapper
import com.example.kotlinTeam.storage.domain.model.StorageCategory
import com.example.kotlinTeam.storage.domain.model.StorageProduct
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await

@Singleton
class FirestoreRepository @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val storageProductDao: StorageProductDao,
    private val storageMapper: StorageMapper,
) {
    private suspend fun RecipeDto.toRecipeOo(): RecipeOo {
        return RecipeOo(
            id = this.id,
            title = this.title,
            description = this.description,
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

    private suspend fun RecipeWithTimestampDto.toRecipeDto(): RecipeDto {
        return this.recipe?.get()?.await()?.toObject(RecipeDto::class.java)!!
    }

    fun getRecipes(): Flow<PagingData<RecipeOo>> {
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

    fun getRecipesByUserId(id: String): Flow<PagingData<RecipeOo>> {
        val usersCollectionRef = firestore.collection(Constants.USERS_COLLECTION)
        val recipesSubCollectionRef = usersCollectionRef.document(id).collection(Constants.RECIPES_COLLECTION)
        val query = recipesSubCollectionRef.orderBy(Constants.TIMESTAMP_PROPERTY)


        val pagingConfig = PagingConfig(
            pageSize = Constants.PAGE_SIZE,
            enablePlaceholders = false,
            initialLoadSize = Constants.INITIAL_LOAD_SIZE
        )

        return Pager(
            config = pagingConfig,
            pagingSourceFactory = {
                FirestorePagingSource(query) {
                    it.toObject(RecipeWithTimestampDto::class.java)!!.toRecipeDto().toRecipeOo()
                }
            }
        ).flow
    }

    // хранилище продуктов
    fun getCategory(): Flow<PagingData<StorageCategory>> {
        val recipeCollectionRef = firestore.collection(
            com.example.kotlinTeam.storage.common.Constants.CATEGORY_COLLECTION
        )
        val query = recipeCollectionRef.orderBy(
            com.example.kotlinTeam.storage.common.Constants.TITLE_PROPERTY
        )

        val pagingConfig = PagingConfig(
            pageSize = com.example.kotlinTeam.storage.common.Constants.PAGE_SIZE,
            enablePlaceholders = false,
            initialLoadSize = com.example.kotlinTeam.storage.common.Constants.INITIAL_LOAD_SIZE
        )

        return Pager(
            config = pagingConfig,
            pagingSourceFactory = {
                FirestorePagingSource(query) {
                    storageMapper.mapCategory(it.toObject(StorageCategoryDto::class.java))
                }
            }
        ).flow
    }

    fun getProduct(parentId: String): Flow<PagingData<StorageProduct>> {
        val recipeCollectionRef = firestore.collection(
            com.example.kotlinTeam.storage.common.Constants.PRODUCT_COLLECTION
        )

        val query = recipeCollectionRef
            .whereEqualTo("parentId", "/category/$parentId")
            .orderBy(com.example.kotlinTeam.storage.common.Constants.TITLE_PROPERTY)

        val pagingConfig = PagingConfig(
            pageSize = com.example.kotlinTeam.storage.common.Constants.PAGE_SIZE,
            enablePlaceholders = false,
            initialLoadSize = com.example.kotlinTeam.storage.common.Constants.INITIAL_LOAD_SIZE
        )

        return Pager(
            config = pagingConfig,
            pagingSourceFactory = {
                FirestorePagingSource(query) {
                    storageMapper.mapProduct(
                        it.toObject(StorageProductDto::class.java),
                        storageProductDao.getById(
                            it.toObject(StorageProductDto::class.java)?.id.toString()
                        )
                    )
                }
            }
        ).flow
    }

    fun insertUser(user: FirebaseUser) {
        val usersCollectionRef = firestore.collection(Constants.USERS_COLLECTION)
        val docData = hashMapOf(
            "id" to user.uid
        )
        usersCollectionRef.document(user.uid).set(docData)
    }
}
