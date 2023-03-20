package com.example.kotkin_team.storage.data.repository

import com.example.kotkin_team.storage.common.StorageStatuses
import com.example.kotkin_team.storage.data.fake.StorageFakeService
import com.example.kotkin_team.storage.domain.model.StorageCategory
import com.example.kotkin_team.storage.domain.model.StorageProduct
import com.example.kotkin_team.storage.domain.repository.StorageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

// репозиторий (запрос к сервису и преобразование ответа в модель приложения)
@Singleton
class StorageRepositoryImplementation @Inject constructor(
    private val storageFakeService: StorageFakeService
) : StorageRepository {
    override fun getCategory(): Flow<StorageStatuses<List<StorageCategory>>> = flow {
        try {
            emit(StorageStatuses.Loading())
            val recipes = storageFakeService.getCategory().category.map { it.toCategory() }
            emit(StorageStatuses.Success(recipes))
        } catch (e: IOException) {
            emit(StorageStatuses.Error("Не обнаружено соединение с сервером. Проверьте интернет подключение"))
        }
    }

    override fun getProduct(parentId: Int): Flow<StorageStatuses<List<StorageProduct>>> = flow {
        try {
            emit(StorageStatuses.Loading())
            val recipes = storageFakeService.getProduct(parentId).product.map { it.toProduct() }
            emit(StorageStatuses.Success(recipes))
        } catch (e: IOException) {
            emit(StorageStatuses.Error("Не обнаружено соединение с сервером. Проверьте интернет подключение"))
        }
    }
}