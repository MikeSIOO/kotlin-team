package com.example.kotlinTeam.feed.data

import com.example.kotlinTeam.feed.data.remote.FakeApi
import com.example.kotlinTeam.feed.domain.repository.FeedRepository
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.delay

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
