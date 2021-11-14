package com.swapcard.aligurelli.core.network.repositories.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.swapcard.aligurelli.core.ArtistsQuery
import com.swapcard.aligurelli.core.network.repositories.HomeRepository
import com.swapcard.aligurelli.core.network.responses.Artist
import com.swapcard.aligurelli.core.utils.NetworkResult
import kotlinx.coroutines.flow.collect

class ArtistsPagingDataSource(
    private val repository: HomeRepository,
    private val query: String,
) : PagingSource<Int, Artist>() {

    var initial = 15
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Artist> {
        val currentLoadingPageKey = params.key ?: 15
        return try {
            var response : ArtistsQuery.Data ?= null
            repository.getSearchedList(query, currentLoadingPageKey).collect {
                response = when(it){
                    is NetworkResult.Success -> it.data
                    else -> null
                }
            }

             val data =response?.search?.artists?.nodes?.map { fetchedArtist ->
                Artist(
                    id = fetchedArtist?.fragments?.artistBasicFragment?.id!!, //should be crash if id is null to notify developers something went wrong on BE side.
                    name = fetchedArtist.fragments.artistBasicFragment.name,
                    disambiguation = fetchedArtist.fragments.artistBasicFragment.disambiguation,
                    type = fetchedArtist.fragments.artistBasicFragment.type,
                )
            }

            data?.let {
                val prevKey = if (currentLoadingPageKey == 1) null else currentLoadingPageKey - initial
                LoadResult.Page(
                    data = it.subList(if(currentLoadingPageKey == 15) 0 else currentLoadingPageKey.minus(initial),currentLoadingPageKey),
                    prevKey = prevKey,
                    nextKey = currentLoadingPageKey.plus(initial)
                )
            } ?: kotlin.run {
                LoadResult.Error(Exception("error"))

            }

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Artist>): Int = 15
    override val keyReuseSupported: Boolean = true

}