package com.swapcard.aligurelli.core.utils

sealed class NetworkResult<T> {
    class Success<T>(val data: T) : NetworkResult<T>()
    class Failed<T>(val message: String) : NetworkResult<T>()
    class Loading<T> : NetworkResult<T>()
}