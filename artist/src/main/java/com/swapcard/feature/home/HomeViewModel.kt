package com.swapcard.feature.home

import androidx.lifecycle.*
import androidx.paging.*
import com.swapcard.aligurelli.core.database.artistbookmark.BookmarkRepository
import com.swapcard.aligurelli.core.database.artistbookmark.BookmarkedArtist
import com.swapcard.aligurelli.core.network.repositories.HomeRepository
import com.swapcard.aligurelli.core.network.repositories.paging.ArtistsPagingDataSource
import com.swapcard.aligurelli.core.utils.DEFAULT_PAGE_SIZE
import com.swapcard.aligurelli.core.utils.DEFAULT_PREFETCHED_DISTANCE
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(private val homeRepository: HomeRepository, private val bookmarkRepository: BookmarkRepository) : ViewModel() {

    private var _dataPagingSource: ArtistsPagingDataSource? = null
    private val _requestChannel = ConflatedBroadcastChannel<String>()


    private val _searchedArtistList = Pager(
        config = PagingConfig(
            pageSize = DEFAULT_PAGE_SIZE,
            prefetchDistance = DEFAULT_PREFETCHED_DISTANCE
        ),
        pagingSourceFactory = {
            _dataPagingSource = ArtistsPagingDataSource(
                repository = homeRepository,
                query = _requestChannel.valueOrNull ?: "",
            )
            _dataPagingSource!!
        }
    ).flow.cachedIn(viewModelScope).asLiveData()

    val searchedArtistList: LiveData<PagingData<com.swapcard.aligurelli.core.network.responses.Artist>>
        get() = _searchedArtistList


    private val _bookMarkedArtistList = MutableLiveData<List<BookmarkedArtist>>()
    val bookMarkedArtistList: LiveData<List<BookmarkedArtist>>
        get() = _bookMarkedArtistList


    init {
        _requestChannel
            .asFlow()
            .onEach { _dataPagingSource?.invalidate() }
            .launchIn(viewModelScope)
    }


    @ObsoleteCoroutinesApi
    suspend fun updateRequest(query: String) {
        _requestChannel.send(query)
    }

    fun getBookmarkedArtists(){
        viewModelScope.launch {
            _bookMarkedArtistList.value = bookmarkRepository.getAllBookmarkedArtist() }
    }



}