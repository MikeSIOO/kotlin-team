package com.example.kotkin_team.products.common

// Статусы получания данных
sealed class Statuses<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : Statuses<T>(data)
    class Error<T>(message: String, data: T? = null) : Statuses<T>(data, message)
    class Loading<T>(data: T? = null) : Statuses<T>(data)
}