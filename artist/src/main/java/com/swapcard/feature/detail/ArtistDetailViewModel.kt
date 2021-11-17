package com.swapcard.feature.detail

import androidx.lifecycle.*
import com.swapcard.aligurelli.core.database.artistbookmark.BookmarkRepository
import com.swapcard.aligurelli.core.network.repositories.ArtistDetailRepository
import com.swapcard.aligurelli.core.network.responses.ArtistDetail
import com.swapcard.aligurelli.core.utils.NetworkResult
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class ArtistDetailViewModel(
    private val artistArtistDetailRepo: ArtistDetailRepository,
    private val bookmarkRepo: BookmarkRepository
) : ViewModel() {

    private lateinit var artistID: String

    private val _data = MutableLiveData<ArtistDetail>()
    val data: LiveData<ArtistDetail>
        get() = _data


    private val _state = MutableLiveData<ArtistDetailViewState>()
    val state: LiveData<ArtistDetailViewState>
        get() = _state


    fun setArtistIDToFetch(id: String?) {
        id?.let { artistID = it }

    }


    fun getArtistDetail() {

        viewModelScope.launch {
            artistArtistDetailRepo.getArtistDetail(artistID).collect {
                when (it) {
                    is NetworkResult.Success -> {
                        //set data
                        _data.value = ArtistDetail(
                            id = it.data.node?.fragments?.artistDetailsFragment?.id!!,
                            name = it.data.node?.fragments?.artistDetailsFragment?.name,
                            disambiguation = it.data.node?.fragments?.artistDetailsFragment?.disambiguation,
                            rating = it.data.node?.fragments?.artistDetailsFragment?.rating?.value
                        )

                        //change state
                        _state.value = ArtistDetailViewState.FETCHED

                        //check bookmark status
                        bookmarkRepo.getBookmarkedArtistWithID(artistID)?.let {
                            _state.value = ArtistDetailViewState.AlreadyAddedToFavorite
                        } ?: run {
                            _state.value = ArtistDetailViewState.AddToFavorite
                        }

                    }

                    is NetworkResult.Failed -> _state.value = ArtistDetailViewState.Error
                }

            }
        }
    }

    fun addArtistToBookmarks() {
        _data.value?.let {
            viewModelScope.launch {
                bookmarkRepo.insertArtistToBookmarks(
                    id = it.id,
                    name = it.name!!,
                    disambiguation = it.disambiguation!!,
                )

                _state.value = ArtistDetailViewState.AddedToFavorite
            }
        }
    }

}