package com.example.kotkin_team.storage.domain.use_cases

data class StorageUseCases(
    val storageGetCategory: StorageGetCategory,
    val storageGetProduct: StorageGetProduct,
    val storageSelectProduct: StorageSelectProduct,
)