package com.swapcard.feature.home

import androidx.lifecycle.*
import androidx.paging.*
import com.swapcard.aligurelli.core.ArtistsQuery
import com.swapcard.aligurelli.core.network.repositories.HomeRepository
import com.swapcard.aligurelli.core.network.repositories.paging.ArtistsPagingDataSource
import com.swapcard.aligurelli.core.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(private val homeRepository: HomeRepository) : ViewModel() {

    private val AT_SEARCH_SECTION = 0

    private var _dataPagingSource: ArtistsPagingDataSource? = null
    private val _requestChannel = ConflatedBroadcastChannel<String>()

    private val _state = MutableLiveData<HomeViewState>()
    val state: LiveData<HomeViewState>
        get() = _state


    private  val _searchedArtistList = Pager(
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

    val searchedArtistList : LiveData<PagingData<com.swapcard.aligurelli.core.network.responses.Artist>>
        get() = _searchedArtistList


    init {
        _requestChannel
            .asFlow()
            .onEach { _dataPagingSource?.invalidate() }
            .launchIn(viewModelScope)
    }

    fun onTabChanged(tabPosition: Int) {
        if (tabPosition == AT_SEARCH_SECTION) _state.value = HomeViewState.InSearchTab
    }


    @ObsoleteCoroutinesApi
    suspend fun updateRequest(query: String) {
        _requestChannel.send(query)
    }

}