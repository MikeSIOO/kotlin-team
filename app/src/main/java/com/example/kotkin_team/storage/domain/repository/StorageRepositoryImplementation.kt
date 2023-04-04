package com.example.kotkin_team.storage.domain.repository

import com.example.kotkin_team.storage.common.StorageStatuses
import com.example.kotkin_team.storage.data.api.service.StorageFakeService
import com.example.kotkin_team.storage.data.db.service.StorageProductDao
import com.example.kotkin_team.storage.data.mapper.StorageCategoryMapper
import com.example.kotkin_team.storage.data.mapper.StorageProductMapper
import com.example.kotkin_team.storage.domain.model.StorageCategory
import com.example.kotkin_team.storage.domain.model.StorageProduct
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

// репозиторий (запрос к сервису и преобразование ответа в модель приложения)
@Singleton
class StorageRepositoryImplementation @Inject constructor(
    private val storageApiService: StorageFakeService,
    private val storageProductDao: StorageProductDao,
    private val storageCategoryMapper: StorageCategoryMapper,
    private val storageProductMapper: StorageProductMapper,
) : StorageRepository {
    override fun getCategory(): Flow<StorageStatuses<List<StorageCategory>>> = flow {
        try {
            emit(StorageStatuses.Loading())
            val storageCategoryDto = storageApiService.getCategory().category
            val storageCategory = storageCategoryMapper.map(storageCategoryDto)
            emit(StorageStatuses.Success(storageCategory))
        } catch (e: IOException) {
            emit(StorageStatuses.Error("Не обнаружено соединение с сервером. Проверьте интернет подключение"))
        }
    }

    override fun getProduct(parentId: Int): Flow<StorageStatuses<List<StorageProduct>>> = flow {
        try {
            emit(StorageStatuses.Loading())
            val storageProductDto = storageApiService.getProduct(parentId).product
            val storageProductEntity = storageProductDao.getByParent(parentId)
            val storageProduct = storageProductMapper.map(storageProductDto, storageProductEntity)
            emit(StorageStatuses.Success(storageProduct))
        } catch (e: IOException) {
            emit(StorageStatuses.Error("Не обнаружено соединение с сервером. Проверьте интернет подключение"))
        }
    }

    override fun selectProduct(storageProduct: StorageProduct): Flow<StorageStatuses<StorageProduct>> =
        flow {
            try {
                emit(StorageStatuses.Loading())
                val storageProductEntity = storageProductMapper.mapToEntity(storageProduct)
                if (storageProduct.selected) {
                    storageProductDao.delete(storageProductEntity)
                } else {
                    storageProductDao.insert(storageProductEntity)
                }
                storageProduct.select()
                emit(StorageStatuses.Success(storageProduct))
            } catch (e: IOException) {
                emit(StorageStatuses.Error("Не обнаружено соединение с сервером. Проверьте интернет подключение"))
            }
        }
}
