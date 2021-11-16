package com.swapcard.feature.detail

import androidx.lifecycle.*
import com.swapcard.aligurelli.core.database.artistbookmark.BookmarkRepository
import com.swapcard.aligurelli.core.network.repositories.DetailRepository
import com.swapcard.aligurelli.core.network.responses.ArtistDetailResponse
import com.swapcard.aligurelli.core.utils.NetworkResult
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class ArtistDetailViewModel(private val artistDetailRepo: DetailRepository, private val bookmarkRepo: BookmarkRepository) : ViewModel() {

    private lateinit var artistID: String

    private val _data = MutableLiveData<ArtistDetailResponse>()
    val data: LiveData<ArtistDetailResponse>
        get() = _data


    private val _state = MutableLiveData<ArtistDetailViewState>()
    val state: LiveData<ArtistDetailViewState>
        get() = _state


    fun setArtistIDToFetch(id: String?) {
        id?.let { artistID = it }

    }


    fun getArtistDetail() {
        _state.postValue(ArtistDetailViewState.Loading)

        viewModelScope.launch {
            artistDetailRepo.getArtistDetail(artistID).collect {
                _state.postValue(ArtistDetailViewState.Loading)

                when (it) {
                    is NetworkResult.Success -> {
                        _data.value = ArtistDetailResponse(
                            id = it.data.node?.fragments?.artistDetailsFragment?.id!!,
                            name = it.data.node?.fragments?.artistDetailsFragment?.name,
                            disambiguation = it.data.node?.fragments?.artistDetailsFragment?.disambiguation,
                            rating = it.data.node?.fragments?.artistDetailsFragment?.rating?.value
                        )

                        _state.postValue(ArtistDetailViewState.FETCHED)


                        bookmarkRepo.getBookmarkedArtistWithID(artistID)?.let {
                            _state.postValue(ArtistDetailViewState.AlreadyAddedToFavorite)
                        } ?: run {
                            _state.postValue(ArtistDetailViewState.AddToFavorite)
                        }

                    }

                    is NetworkResult.Failed -> _state.postValue(ArtistDetailViewState.Error)

                }

            }
        }
    }

    fun addArtistToBookmarks(){
        _data.value?.let {
            viewModelScope.launch {
                bookmarkRepo.insertArtistToBookmarks(
                    id = it.id,
                    name = it.name!!,
                    disambiguation = it.disambiguation!!,
                )

                _state.postValue(ArtistDetailViewState.AddedToFavorite)
            }
        }
    }

}