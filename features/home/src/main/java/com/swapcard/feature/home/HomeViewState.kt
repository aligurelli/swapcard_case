package com.swapcard.feature.home

import com.swarcards.commons.ui.base.BaseViewState

sealed class HomeViewState : BaseViewState {


    object InSearchTab : HomeViewState()
    fun isSearchTab() = this is InSearchTab

}
