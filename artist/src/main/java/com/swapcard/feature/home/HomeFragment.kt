package com.swapcard.feature.home

import android.os.Bundle
import com.swapcard.feature.home.adapter.HomepagePagerAdapter
import com.swapcard.feature.home.databinding.FragmentHomeBinding
import com.swapcard.feature.di.ArtistViewModelModule
import com.swapcard.feature.home.adapter.BookmarkedArtistsAdapter
import com.swapcard.feature.home.tab.BookmarkedArtistFragment
import com.swapcard.feature.home.tab.SearchArtistFragment
import com.swarcards.commons.ui.base.BaseFragment
import com.swarcards.commons.ui.extensions.observe
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.ObsoleteCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules


class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(layoutId = R.layout.fragment_home){


    override val viewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadKoinModules(ArtistViewModelModule)



    }

    override fun onInitDataBinding() {

    }

    @ObsoleteCoroutinesApi
    @FlowPreview
    @ExperimentalCoroutinesApi
    override fun prepareView() {
        //setup viewpager
        val adapter = HomepagePagerAdapter(this.childFragmentManager).apply {
            addFragment(SearchArtistFragment(), "Artists")
            addFragment(BookmarkedArtistFragment(), "Bookmarked")
        }

        binding.viewpagerFragmentHome.adapter = adapter

        //bind tablayout with pager
        binding.tablayoutFragmentHome.setupWithViewPager(binding.viewpagerFragmentHome)

    }


    override fun observe() {
       observe(viewModel.searchedArtistList) {

       }
    }

}