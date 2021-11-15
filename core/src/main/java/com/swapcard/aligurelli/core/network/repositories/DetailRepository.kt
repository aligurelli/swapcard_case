package com.swapcard.aligurelli.core.network.repositories

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.await
import com.swapcard.aligurelli.core.ArtistDetailQuery
import com.swapcard.aligurelli.core.utils.NetworkBoundRepository
import com.swapcard.aligurelli.core.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class DetailRepository(private val apolloClient: ApolloClient) {



    fun getArtistDetail(id : String): Flow<NetworkResult<ArtistDetailQuery.Data>> {
        return object : NetworkBoundRepository<ArtistDetailQuery.Data>(){
            override suspend fun fetchFromRemote(): Response<ArtistDetailQuery.Data> {
                return apolloClient.query(ArtistDetailQuery(id)).await()
            }

        }.asFlow().flowOn(Dispatchers.IO)
    }


}




