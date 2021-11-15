package com.swapcard.feature.detail

import android.os.Bundle
import com.swapcard.aligurelli.core.utils.ARTIST_ID
import com.swapcard.feature.home.R
import com.swapcard.feature.home.databinding.FragmentDetailBinding
import com.swarcards.commons.ui.base.BaseFragment
import com.swarcards.commons.ui.extensions.parsePrimitives
import org.koin.androidx.viewmodel.ext.android.viewModel

class ArtistDetailFragment :
    BaseFragment<FragmentDetailBinding, ArtistDetailViewModel>(layoutId = R.layout.fragment_detail) {
    override val viewModel: ArtistDetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val passedArtistID = parsePrimitives<String>(ARTIST_ID)
        viewModel.setArtistIDToFetch(passedArtistID)
    }

    override fun prepareView() {

    }

    override fun onInitDataBinding() {

    }

    override fun observe() {
        viewModel.getArtistDetail()

    }

}