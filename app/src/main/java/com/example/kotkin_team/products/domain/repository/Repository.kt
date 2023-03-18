package com.example.kotkin_team.products.domain.repository

import com.example.kotkin_team.products.common.Statuses
import com.example.kotkin_team.products.domain.model.Category
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getCategory(page: Int): Flow<Statuses<List<Category>>>
}