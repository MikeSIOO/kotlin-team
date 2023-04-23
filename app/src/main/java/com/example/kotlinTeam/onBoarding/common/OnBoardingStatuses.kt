package com.example.kotlinTeam.onBoarding.common

// Статусы получания данных
sealed class OnBoardingStatuses<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : OnBoardingStatuses<T>(data)
    class Error<T>(message: String, data: T? = null) : OnBoardingStatuses<T>(data, message)
    class Loading<T>(data: T? = null) : OnBoardingStatuses<T>(data)
}
