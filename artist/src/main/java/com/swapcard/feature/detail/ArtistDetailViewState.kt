package com.swapcard.feature.detail

import com.swarcards.commons.ui.base.BaseViewState

sealed class ArtistDetailViewState : BaseViewState {


    object Loading : ArtistDetailViewState()

    object FETCHED : ArtistDetailViewState()

    object Error : ArtistDetailViewState()

    object AddToFavorite : ArtistDetailViewState()

    object AddedToFavorite : ArtistDetailViewState()

    object AlreadyAddedToFavorite : ArtistDetailViewState()



    fun isAddToFavorite() = this is AddToFavorite

}
