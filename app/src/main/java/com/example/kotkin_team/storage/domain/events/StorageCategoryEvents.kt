package com.example.kotkin_team.storage.domain.events

sealed class StorageCategoryEvents {
    object LoadCategory : StorageCategoryEvents()
}