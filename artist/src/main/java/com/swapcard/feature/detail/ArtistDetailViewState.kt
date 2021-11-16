package com.swapcard.feature.detail

import com.swarcards.commons.ui.base.BaseViewState

sealed class ArtistDetailViewState : BaseViewState {


    object Loading : ArtistDetailViewState()

    object FETCHED : ArtistDetailViewState()

    object Error : ArtistDetailViewState()

    object AddToFavorite : ArtistDetailViewState()

    object AddedToFavorite : ArtistDetailViewState()

    object AlreadyAddedToFavorite : ArtistDetailViewState()


    /**
     * Check if current view state is [AddToFavorite].
     * means that the artist can be added to bookmarks(not bookmarked yet)
     */
    fun isAddToFavorite() = this is AddToFavorite

}
