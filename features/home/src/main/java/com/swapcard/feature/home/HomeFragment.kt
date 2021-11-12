package com.swapcard.feature.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.swapcard.aligurelli.core.network.repositories.SampleRepo
import com.swapcard.aligurelli.core.utils.NetworkResult
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class HomeFragment : Fragment() {

    val sampleRepo: SampleRepo by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
                sampleRepo.send().collect {
                    when(it){
                        is NetworkResult.Success -> Log.e("tahh", it.data.node?.fragments?.artistDetailsFragment?.name!!)

                    }
                }
        }

    }
}