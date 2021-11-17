package com.swapcard.aligurelli.core.network.repositories

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Input
import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.await
import com.swapcard.aligurelli.core.ArtistDetailQuery
import com.swapcard.aligurelli.core.ArtistsQuery
import com.swapcard.aligurelli.core.utils.DEFAULT_PAGE_SIZE
import com.swapcard.aligurelli.core.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class HomeRepository(private val apolloClient: ApolloClient) : BaseRepo() {


    suspend fun getSearchedList(query: String, first: Int = DEFAULT_PAGE_SIZE) =
        flow<NetworkResult<ArtistsQuery.Data>> {
            emit(
                safeApiCall {
                    apolloClient.query(
                        ArtistsQuery(
                            query = query,
                            first = Input.fromNullable(first)
                        )
                    ).await()
                }
            )
        }.flowOn(Dispatchers.IO)


}




