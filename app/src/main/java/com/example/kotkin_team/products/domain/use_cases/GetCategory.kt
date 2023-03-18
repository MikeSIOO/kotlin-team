package com.example.kotkin_team.products.domain.use_cases

import com.example.kotkin_team.products.common.Statuses
import com.example.kotkin_team.products.domain.model.Category
import com.example.kotkin_team.products.domain.repository.Repository
import kotlinx.coroutines.flow.Flow

class GetCategory(
    private val repository: Repository
) {
    operator fun invoke(page: Int): Flow<Statuses<List<Category>>> {
        return repository.getCategory(page)
    }
}