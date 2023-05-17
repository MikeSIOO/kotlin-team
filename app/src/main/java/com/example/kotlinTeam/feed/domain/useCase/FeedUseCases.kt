package com.example.kotlinTeam.feed.domain.useCase

import javax.inject.Inject

data class FeedUseCases @Inject constructor(
    val getFeedUseCase: GetFeedUseCase,
    val saveMadeRecipeUseCase: SaveMadeRecipeUseCase,
    val getSelectedProductsUseCase: GetSelectedProductsUseCase
)
