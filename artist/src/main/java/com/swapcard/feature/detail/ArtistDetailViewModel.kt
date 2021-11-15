package com.swapcard.feature.detail

import androidx.lifecycle.*
import com.swapcard.aligurelli.core.ArtistDetailQuery
import com.swapcard.aligurelli.core.network.repositories.DetailRepository
import com.swapcard.aligurelli.core.network.responses.ArtistDetail
import com.swapcard.aligurelli.core.utils.NetworkResult
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ArtistDetailViewModel(private val artistDetailRepo: DetailRepository) : ViewModel() {

    private lateinit var artistID: String

    val _onArtistDetailFetched = MutableLiveData<ArtistDetail?>()
    val onArtistDetailFetched: LiveData<ArtistDetail?>
        get() = _onArtistDetailFetched


    private val _state = Transformations.map(_onArtistDetailFetched) {
        it?.let {
            ArtistDetailViewState.FETCHED
        }?: kotlin.run { ArtistDetailViewState.ERROR }
    }
    val state: LiveData<ArtistDetailViewState>
        get() = _state



    fun setArtistIDToFetch(id: String?) {
        id?.let {  artistID = it }

    }

    fun getArtistDetail() {
        viewModelScope.launch {
            artistDetailRepo.getArtistDetail(artistID).collect {
                when (it) {
                    is NetworkResult.Success -> _onArtistDetailFetched.value = ArtistDetail(
                        id = it.data.node?.fragments?.artistDetailsFragment?.id!!,
                        name = it.data.node?.fragments?.artistDetailsFragment?.name,
                        disambiguation = it.data.node?.fragments?.artistDetailsFragment?.disambiguation,
                        rating = it.data.node?.fragments?.artistDetailsFragment?.rating?.value
                    )
                    is NetworkResult.Failed -> _onArtistDetailFetched.value = null
                }
            }
        }

    }
}