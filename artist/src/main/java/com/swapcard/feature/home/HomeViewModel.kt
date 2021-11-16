package com.swapcard.feature.home

import androidx.lifecycle.*
import androidx.paging.*
import com.swapcard.aligurelli.core.database.artistbookmark.BookmarkRepository
import com.swapcard.aligurelli.core.database.artistbookmark.BookmarkedArtist
import com.swapcard.aligurelli.core.network.repositories.HomeRepository
import com.swapcard.aligurelli.core.network.repositories.paging.ArtistsPagingDataSource
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(private val homeRepository: HomeRepository, private val bookmarkRepository: BookmarkRepository) : ViewModel() {

    private val AT_SEARCH_SECTION = 0

    private var _dataPagingSource: ArtistsPagingDataSource? = null
    private val _requestChannel = ConflatedBroadcastChannel<String>()


    private val _searchedArtistList = Pager(
        config = PagingConfig(
            pageSize = 15,
            prefetchDistance = 2
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