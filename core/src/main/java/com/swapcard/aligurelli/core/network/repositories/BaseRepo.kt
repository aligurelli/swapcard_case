package com.swapcard.aligurelli.core.network.repositories

import com.apollographql.apollo.api.Response
import com.swapcard.aligurelli.core.utils.NetworkResult

abstract class BaseRepo {

    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): NetworkResult<T> {
        try {

            val response = apiCall()
            if (!response.hasErrors()) {
                val body = response.data
                body?.let {
                    return NetworkResult.Success(body)
                }
            }
            return error("${response.errors?.get(0)?.message}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(errorMessage: String): NetworkResult<T> =
        NetworkResult.Failed("Api call failed $errorMessage")
}