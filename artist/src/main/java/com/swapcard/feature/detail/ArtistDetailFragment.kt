package com.swapcard.feature.detail

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.swapcard.aligurelli.core.utils.ARTIST_ID
import com.swapcard.feature.home.R
import com.swapcard.feature.home.databinding.FragmentDetailBinding
import com.swapcards.common.view.ProgressBarDialog
import com.swarcards.commons.ui.base.BaseFragment
import com.swarcards.commons.ui.extensions.observe
import com.swarcards.commons.ui.extensions.parsePrimitives
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ArtistDetailFragment :
    BaseFragment<FragmentDetailBinding, ArtistDetailViewModel>(layoutId = R.layout.fragment_detail) {

    override val viewModel: ArtistDetailViewModel by viewModel()

    private val progressDialog by inject<ProgressBarDialog>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setArtistIDToFetch(parsePrimitives(ARTIST_ID))
    }

    override fun prepareView() {

    }


    override fun onInitDataBinding() {
        binding.viewModel = viewModel
        binding.executePendingBindings()

    }

    override fun observe() {
        viewModel.getArtistDetail()
        observe(viewModel.state, ::onStateChanged)
    }



    private fun onStateChanged(viewState: ArtistDetailViewState){
        when (viewState) {
           /* is ArtistDetailViewState.Loading ->
               // progressDialog.show(R.string.loading)
            is ArtistDetailViewState.Error ->
                //progressDialog.dismissWithErrorMessage(R.string.error)*/
            is ArtistDetailViewState.AddedToFavorite ->
                Snackbar.make(
                    requireView(),
                    R.string.added_into_bookmarks,
                    Snackbar.LENGTH_LONG
                ).show()
           /* is ArtistDetailViewState.FETCHED ->
                //progressDialog.dismiss()*/
        }


        binding.invalidateAll()
    }

}