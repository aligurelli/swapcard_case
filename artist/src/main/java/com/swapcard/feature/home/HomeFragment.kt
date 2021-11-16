package com.swapcard.feature.home

import android.os.Bundle
import com.swapcard.feature.home.adapter.HomepageTablayoutAdapter
import com.swapcard.feature.home.databinding.FragmentHomeBinding
import com.swapcard.feature.di.ArtistViewModelModule
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
        val adapter = HomepageTablayoutAdapter(this.childFragmentManager)
        adapter.addFragment(TabFragment(), "Artists")
        adapter.addFragment(TabFragment(), "Bookmarked")

        binding.viewpagerFragmentHome.adapter = adapter

        //bind tablayout with pager
        binding.tablayoutFragmentHome.setupWithViewPager(binding.viewpagerFragmentHome)

    }


    override fun observe() {
       observe(viewModel.searchedArtistList) {

       }
    }

}