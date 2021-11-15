package com.swapcard.feature.home

import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.swapcard.aligurelli.core.utils.ARTIST_ID
import com.swapcard.feature.home.adapter.ArtistAdapter
import com.swapcard.feature.home.databinding.FragmentTabBinding
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

class TabFragment : BaseFragment<FragmentTabBinding, HomeViewModel>(R.layout.fragment_tab) {
    override val viewModel: HomeViewModel by viewModel()

    private  val searchedArtistListAdapter = ArtistAdapter {
        navigateToNextFragment(R.id.actionArtistDetail){
            putString(ARTIST_ID, it.id)
        }
    }

    @ObsoleteCoroutinesApi
    override fun prepareView() {
        binding.rvArtists.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = searchedArtistListAdapter
        }

        binding.etSearch.textChanges().debounce(500)
            .onEach { viewModel.updateRequest(it?.toString()!!) }
            .launchIn(lifecycleScope)
    }

    override fun onInitDataBinding() {
        binding.searchVisible = true
    }

    override fun observe() {
       observe(viewModel.searchedArtistList){
           lifecycleScope.launch { searchedArtistListAdapter.submitData(it) }
       }
    }
}