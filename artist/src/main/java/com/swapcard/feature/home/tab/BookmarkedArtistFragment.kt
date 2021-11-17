package com.swapcard.feature.home.tab

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.swapcard.aligurelli.core.utils.GRID_COUNT
import com.swapcard.feature.home.HomeViewModel
import com.swapcard.feature.home.R
import com.swapcard.feature.home.adapter.BookmarkedArtistsAdapter
import com.swapcard.feature.home.databinding.FragmentBookmarkedArtistBinding
import com.swarcards.commons.ui.base.BaseFragment
import com.swarcards.commons.ui.extensions.observe
import kotlinx.coroutines.ObsoleteCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel

class BookmarkedArtistFragment :
    BaseFragment<FragmentBookmarkedArtistBinding, HomeViewModel>(R.layout.fragment_bookmarked_artist) {
    override val viewModel: HomeViewModel by viewModel()

    private val bookmarkedListAdapter = BookmarkedArtistsAdapter()

    @ObsoleteCoroutinesApi
    override fun prepareView() {
        binding.rvArtists.apply {
            layoutManager = GridLayoutManager(context, GRID_COUNT)
            adapter = bookmarkedListAdapter
        }
    }


    override fun observe() {
        viewModel.getBookmarkedArtists()
        observe(viewModel.bookMarkedArtistList) {
            bookmarkedListAdapter.submitList(it)
        }
    }

    override fun onInitDataBinding() {
    }
}