package com.swapcard.aligurelli.core.network.repositories

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Input
import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.await
import com.swapcard.aligurelli.core.ArtistsQuery
import com.swapcard.aligurelli.core.utils.NetworkBoundRepository
import com.swapcard.aligurelli.core.utils.NetworkResult
import com.swapcard.aligurelli.core.utils.SEARCH_TIME_INTERVAL
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class HomeRepository(private val apolloClient: ApolloClient) {



    fun getSearchedList(query : String, first : Int = 15): Flow<NetworkResult<ArtistsQuery.Data>> {
        return object : NetworkBoundRepository<ArtistsQuery.Data>(){
            override suspend fun fetchFromRemote(): Response<ArtistsQuery.Data> {
                return apolloClient.query(ArtistsQuery(query, Input.fromNullable(first))).await()
            }

        }.asFlow().flowOn(Dispatchers.IO)
    }


}




