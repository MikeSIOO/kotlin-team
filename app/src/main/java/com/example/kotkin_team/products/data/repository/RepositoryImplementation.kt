package com.example.kotkin_team.products.data.repository

import com.example.kotkin_team.products.common.Statuses
import com.example.kotkin_team.products.data.fake.Service
import com.example.kotkin_team.products.domain.model.Category
import com.example.kotkin_team.products.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

// репозиторий (запрос к сервису и преобразование ответа в модель приложения)
@Singleton
class RepositoryImplementation @Inject constructor(
    private val service: Service
) : Repository {
    override fun getCategory(page: Int): Flow<Statuses<List<Category>>> = flow {
        try {
            emit(Statuses.Loading<List<Category>>())
            val recipes = service.getFakeProducts(page).category.map { it.toCategory() }
            emit(Statuses.Success<List<Category>>(recipes))
        } catch (e: IOException) {
            emit(Statuses.Error<List<Category>>("Не обнаружено соединение с сервером. Проверьте интернет подключение"))
        }
    }
}