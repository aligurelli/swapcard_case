package com.swapcard.aligurelli.core.di

import com.swapcard.aligurelli.core.network.repositories.DetailRepository
import com.swapcard.aligurelli.core.network.repositories.HomeRepository
import com.swapcard.aligurelli.core.network.repositories.paging.ArtistsPagingDataSource
import org.koin.dsl.module

val repositoryModule = module {
    single { HomeRepository(get()) }
    single { DetailRepository(get()) }
    single { ArtistsPagingDataSource(get(), get()) }



}