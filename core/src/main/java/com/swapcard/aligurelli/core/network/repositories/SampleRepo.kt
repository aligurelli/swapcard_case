package com.swapcard.aligurelli.core.network.repositories

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.await
import com.swapcard.aligurelli.core.ArtistQuery
import com.swapcard.aligurelli.core.utils.NetworkBoundRepository
import com.swapcard.aligurelli.core.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class SampleRepo(private val apolloClient: ApolloClient) : BaseRepo() {

    fun send(): Flow<NetworkResult<ArtistQuery.Data>> {
        return object : NetworkBoundRepository<ArtistQuery.Data>() {
            override suspend fun fetchFromRemote(): Response<ArtistQuery.Data> {

                return apolloClient.query(ArtistQuery()).await()
            }

        }.asFlow().flowOn(Dispatchers.IO)
    }


}




