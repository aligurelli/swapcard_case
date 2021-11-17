package com.swapcard.feature.home.tab

import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.swapcard.aligurelli.core.utils.ARTIST_ID
import com.swapcard.aligurelli.core.utils.GRID_COUNT
import com.swapcard.feature.home.HomeViewModel
import com.swapcard.feature.home.R
import com.swapcard.feature.home.adapter.ArtistListAdapter
import com.swapcard.feature.home.databinding.FragmentArtistListBinding
import com.swarcards.commons.ui.base.BaseFragment
import com.swarcards.commons.ui.extensions.navigateToNextFragment
import com.swarcards.commons.ui.extensions.observe
import com.swarcards.commons.ui.extensions.textChanges
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchArtistFragment : BaseFragment<FragmentArtistListBinding, HomeViewModel>(R.layout.fragment_artist_list) {
    override val viewModel: HomeViewModel by viewModel()

    private  val searchedArtistListAdapter = ArtistListAdapter {
        navigateToNextFragment(R.id.actionArtistDetail){
            putString(ARTIST_ID, it.id)
        }
    }

    @ObsoleteCoroutinesApi
    override fun prepareView() {
        binding.rvArtists.apply {
            layoutManager = GridLayoutManager(context, GRID_COUNT)
            adapter = searchedArtistListAdapter
        }

        binding.etSearch.textChanges().debounce(500)
            .onEach { viewModel.updateRequest(it?.toString()!!) }
            .launchIn(lifecycleScope)
    }


    override fun observe() {
       observe(viewModel.searchedArtistList){
           lifecycleScope.launch { searchedArtistListAdapter.submitData(it) }
       }
    }

    override fun onInitDataBinding() {
    }
}