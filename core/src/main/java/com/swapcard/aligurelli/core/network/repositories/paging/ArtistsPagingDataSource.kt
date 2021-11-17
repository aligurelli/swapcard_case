package com.swapcard.aligurelli.core.network.repositories.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.swapcard.aligurelli.core.ArtistsQuery
import com.swapcard.aligurelli.core.network.repositories.HomeRepository
import com.swapcard.aligurelli.core.network.responses.Artist
import com.swapcard.aligurelli.core.utils.DEFAULT_PAGE_SIZE
import com.swapcard.aligurelli.core.utils.ERROR_MESSAGE
import com.swapcard.aligurelli.core.utils.NetworkResult
import kotlinx.coroutines.flow.collect

class ArtistsPagingDataSource(
    private val repository: HomeRepository,
    private val query: String,
) : PagingSource<Int, Artist>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Artist> {
        val currentLoadingPageKey = params.key ?: DEFAULT_PAGE_SIZE
        return try {
            var response : ArtistsQuery.Data ?= null

            //get searched query by pagination from remote
            getSearchedQueryByPagination(currentLoadingPageKey).collect {
                response = when(it){
                    is NetworkResult.Success -> it.data
                    else -> null
                }
            }

            //map to our object
             val data =response?.search?.artists?.nodes?.map { fetchedArtist ->
                Artist(
                    id = fetchedArtist?.fragments?.artistBasicFragment?.id!!, //should be crash if id is null to notify developers something went wrong on BE side.
                    name = fetchedArtist.fragments.artistBasicFragment.name,
                    disambiguation = fetchedArtist.fragments.artistBasicFragment.disambiguation,
                    type = fetchedArtist.fragments.artistBasicFragment.type,
                )
            }

            //adjust pagination params
            data?.let {
                val prevKey = if (currentLoadingPageKey == 1) null else currentLoadingPageKey - DEFAULT_PAGE_SIZE
                LoadResult.Page(
                    data = it.subList(if(currentLoadingPageKey == DEFAULT_PAGE_SIZE) 0 else currentLoadingPageKey.minus(DEFAULT_PAGE_SIZE),currentLoadingPageKey),
                    prevKey = prevKey,
                    nextKey = currentLoadingPageKey.plus(DEFAULT_PAGE_SIZE)
                )
            } ?: kotlin.run {
                LoadResult.Error(Exception(ERROR_MESSAGE))

            }

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Artist>): Int = DEFAULT_PAGE_SIZE
    override val keyReuseSupported: Boolean = true

    private suspend fun getSearchedQueryByPagination(currentLoadingPageKey : Int)  = repository.getSearchedList(query, currentLoadingPageKey)

}