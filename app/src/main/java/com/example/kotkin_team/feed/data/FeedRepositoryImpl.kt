package com.example.kotkin_team.feed.data

import com.example.kotkin_team.feed.data.remote.FakeApi
import com.example.kotkin_team.feed.domain.repository.FeedRepository
import kotlinx.coroutines.delay
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FeedRepositoryImpl @Inject constructor(
    private val fakeApi: FakeApi
) : FeedRepository {
    override suspend fun getRecipes(): List<Recipe> {
        val data = fakeApi.createFakeRecipes()
        delay(1000)
        return data
    }
}
