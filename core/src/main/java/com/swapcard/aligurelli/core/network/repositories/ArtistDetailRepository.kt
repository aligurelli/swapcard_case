package com.swapcard.aligurelli.core.network.repositories

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.coroutines.await
import com.swapcard.aligurelli.core.ArtistDetailQuery
import com.swapcard.aligurelli.core.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ArtistDetailRepository(private val apolloClient: ApolloClient) : BaseRepo() {


    suspend fun getArtistDetail(id: String) = flow<NetworkResult<ArtistDetailQuery.Data>> {
        emit(
            safeApiCall {
                apolloClient.query(ArtistDetailQuery(id = id)).await()
            }
        )
    }.flowOn(Dispatchers.IO)
}





