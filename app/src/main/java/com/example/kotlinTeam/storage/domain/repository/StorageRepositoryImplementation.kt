package com.example.kotlinTeam.storage.domain.repository

import com.example.kotlinTeam.storage.common.StorageStatuses
import com.example.kotlinTeam.storage.data.db.service.StorageProductDao
import com.example.kotlinTeam.storage.data.mapper.StorageMapper
import com.example.kotlinTeam.storage.domain.model.StorageDataModel
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

// репозиторий (запрос к сервису и преобразование ответа в модель приложения)
@Singleton
class StorageRepositoryImplementation @Inject constructor(
    private val storageProductDao: StorageProductDao,
    private val storageMapper: StorageMapper,
) : StorageRepository {
    override fun selectProduct(storageProduct: StorageDataModel.StorageProduct):
        Flow<StorageStatuses<StorageDataModel.StorageProduct>> = flow {
            try {
                emit(StorageStatuses.Loading())
                val storageProductEntity = storageMapper.mapToProductEntity(storageProduct)
                if (storageProduct.selected) {
                    storageProductDao.delete(storageProductEntity)
                } else {
                    storageProductDao.insert(storageProductEntity)
                }
                storageProduct.select()
                emit(StorageStatuses.Success(storageProduct))
            } catch (e: IOException) {
                emit(
                    StorageStatuses.Error(
                        "Не обнаружено соединение с сервером. Проверьте интернет подключение"
                    )
                )
            }
        }

    override fun getSelectedProducts(): Flow<StorageStatuses<List<StorageDataModel.StorageProduct>>> =
        flow {
            try {
                emit(StorageStatuses.Loading())
                storageProductDao.getSelectedProducts().collect {
                    val selectedProducts = storageMapper.mapEntityListToProductList(it)
                    emit(StorageStatuses.Success(selectedProducts))
                }
            } catch (e: IOException) {
                emit(
                    StorageStatuses.Error(
                        e.localizedMessage ?: "Попробуйте позже"
                    )
                )
            }
        }
}
