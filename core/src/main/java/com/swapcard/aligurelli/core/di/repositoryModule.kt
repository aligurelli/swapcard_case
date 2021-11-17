package com.swapcard.aligurelli.core.di

import com.swapcard.aligurelli.core.database.artistbookmark.BookmarkRepository
import com.swapcard.aligurelli.core.network.repositories.ArtistDetailRepository
import com.swapcard.aligurelli.core.network.repositories.HomeRepository
import com.swapcard.aligurelli.core.network.repositories.paging.ArtistsPagingDataSource
import org.koin.dsl.module

val repositoryModule = module {
    single { HomeRepository(get()) }
    factory { ArtistDetailRepository(get()) }
    single { BookmarkRepository(get()) }
    factory { ArtistsPagingDataSource(get(), get()) }



}