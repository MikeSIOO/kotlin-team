package com.example.kotkin_team.storage.common

// Статусы получания данных
sealed class StorageStatuses<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : StorageStatuses<T>(data)
    class Error<T>(message: String, data: T? = null) : StorageStatuses<T>(data, message)
    class Loading<T>(data: T? = null) : StorageStatuses<T>(data)
}
