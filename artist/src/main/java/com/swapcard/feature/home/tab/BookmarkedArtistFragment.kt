package com.swapcard.feature.home.tab

import androidx.recyclerview.widget.LinearLayoutManager
import com.swapcard.feature.home.HomeViewModel
import com.swapcard.feature.home.R
import com.swapcard.feature.home.adapter.BookmarkedArtistsAdapter
import com.swapcard.feature.home.databinding.FragmentTabBinding
import com.swarcards.commons.ui.base.BaseFragment
import com.swarcards.commons.ui.extensions.observe
import kotlinx.coroutines.ObsoleteCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel

class BookmarkedArtistFragment :
    BaseFragment<FragmentTabBinding, HomeViewModel>(R.layout.fragment_tab) {
    override val viewModel: HomeViewModel by viewModel()

    private val bookmarkedListAdapter = BookmarkedArtistsAdapter()

    @ObsoleteCoroutinesApi
    override fun prepareView() {
        binding.rvArtists.apply {
            layoutManager = LinearLayoutManager(context)
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