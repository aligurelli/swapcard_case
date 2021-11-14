package com.swapcard.feature.home.di

import com.swapcard.feature.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val HomeViewModelModule = module {
    viewModel { HomeViewModel(get()) }



}