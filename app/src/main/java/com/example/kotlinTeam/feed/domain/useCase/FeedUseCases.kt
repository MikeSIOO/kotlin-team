package com.example.kotlinTeam.feed.domain.useCase

import com.example.kotlinTeam.profile.domain.useCases.GetProfile
import javax.inject.Inject

data class FeedUseCases @Inject constructor(
    val getFeedUseCase: GetFeedUseCase,
    val getSelectedProductsUseCase: GetSelectedProductsUseCase,
    val getProfile: GetProfile
)
