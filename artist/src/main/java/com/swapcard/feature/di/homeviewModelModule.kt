package com.swapcard.feature.di

import com.swapcard.feature.detail.ArtistDetailViewModel
import com.swapcard.feature.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ArtistViewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { ArtistDetailViewModel(get()) }



}