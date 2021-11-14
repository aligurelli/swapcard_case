package com.swapcard.aligurelli.core.utils

sealed class NetworkResult<T> {
    data class Success<T>(val data: T) : NetworkResult<T>()
    data class Failed<T>(val message: String) : NetworkResult<T>()
}