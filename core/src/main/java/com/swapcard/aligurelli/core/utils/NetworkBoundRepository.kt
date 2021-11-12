package com.swapcard.aligurelli.core.utils

import android.util.Log
import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.api.Response
import kotlinx.coroutines.flow.*


abstract class NetworkBoundRepository<RESPONSE : Operation.Data> {

    fun asFlow() = flow {

        //to show loading
        emit(NetworkResult.Loading())

        //fetch latest data from remote
        val apiResponse = fetchFromRemote()

        //get body
        val remoteData = apiResponse.data

        //Check for response validation
        if (!apiResponse.hasErrors() && remoteData != null) {
            emit(NetworkResult.Success(remoteData))

        } else {
            emit(NetworkResult.Failed(apiResponse.errors?.get(0)?.message!!))
        }

    }.catch { e ->
        Log.e("ERROR", e.message.toString())
        e.printStackTrace()
        emit(NetworkResult.Failed("Network error! Can't get latest data."))
    }

    protected abstract suspend fun fetchFromRemote(): Response<RESPONSE>

}