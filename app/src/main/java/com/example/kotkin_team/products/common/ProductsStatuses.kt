package com.example.kotkin_team.products.common

// Статусы получания данных
sealed class ProductsStatuses<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : ProductsStatuses<T>(data)
    class Error<T>(message: String, data: T? = null) : ProductsStatuses<T>(data, message)
    class Loading<T>(data: T? = null) : ProductsStatuses<T>(data)
}