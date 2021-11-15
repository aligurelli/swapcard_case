package com.swapcard.feature.detail

import com.swarcards.commons.ui.base.BaseViewState

sealed class ArtistDetailViewState : BaseViewState {

    object ERROR : ArtistDetailViewState()

    object FETCHED : ArtistDetailViewState()

}
