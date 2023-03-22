package com.example.kotkin_team.storage.data.repository

import com.example.kotkin_team.storage.common.StorageStatuses
import com.example.kotkin_team.storage.data.api.service.StorageFakeService
import com.example.kotkin_team.storage.data.mapper.StorageCategoryMapper
import com.example.kotkin_team.storage.data.mapper.StorageProductMapper
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
    private val storageFakeService: StorageFakeService,
    private val storageCategoryMapper: StorageCategoryMapper,
    private val storageProductMapper: StorageProductMapper,
) : StorageRepository {
    override fun getCategory(): Flow<StorageStatuses<List<StorageCategory>>> = flow {
        try {
            emit(StorageStatuses.Loading())
            val storageCategoryDto = storageFakeService.getCategory().category
            val storageCategory = storageCategoryMapper.map(storageCategoryDto)
            emit(StorageStatuses.Success(storageCategory))
        } catch (e: IOException) {
            emit(StorageStatuses.Error("Не обнаружено соединение с сервером. Проверьте интернет подключение"))
        }
    }

    override fun getProduct(parentId: Int): Flow<StorageStatuses<List<StorageProduct>>> = flow {
        try {
            emit(StorageStatuses.Loading())
            val storageProductDto = storageFakeService.getProduct(parentId).product
//            val storageProductDao = storageFakeService.getProduct(parentId).product
            val storageProduct = storageProductMapper.map(storageProductDto)
            emit(StorageStatuses.Success(storageProduct))
        } catch (e: IOException) {
            emit(StorageStatuses.Error("Не обнаружено соединение с сервером. Проверьте интернет подключение"))
        }
    }
}