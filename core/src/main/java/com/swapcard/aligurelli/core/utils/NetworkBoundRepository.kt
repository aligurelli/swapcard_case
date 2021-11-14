package com.swapcard.aligurelli.core.utils

import android.util.Log
import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.api.Response
import kotlinx.coroutines.flow.*


abstract class NetworkBoundRepository<RESPONSE> {


    fun asFlow() = flow<NetworkResult<RESPONSE>> {

        // Fetch latest data from remote
        val apiResponse = fetchFromRemote()

        // Parse body
        val remoteData = apiResponse.data

        // Check for response validation
        if (!apiResponse.hasErrors() && remoteData != null) {
            emit(NetworkResult.Success(remoteData))
        } else {
            emit(NetworkResult.Failed("Network error! Can't get latest data."))

        }

    }.catch { e ->
        Log.d("ERROR", e.message.toString())
        e.printStackTrace()
        emit(NetworkResult.Failed("Network error! Can't get latest data."))
    }

    protected abstract suspend fun fetchFromRemote(): Response<RESPONSE>

}