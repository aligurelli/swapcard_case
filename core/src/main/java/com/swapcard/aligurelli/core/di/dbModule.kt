package com.swapcard.aligurelli.core.di

import androidx.room.Room
import com.swapcard.aligurelli.core.BuildConfig
import com.swapcard.aligurelli.core.database.BookmarkedArtistDB
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dbModule = module {

    // Room Database
    single { Room.databaseBuilder(androidContext(), BookmarkedArtistDB::class.java, BuildConfig.SWAPCARD_DATABASE_NAME).build() }

    single { get<BookmarkedArtistDB>().artistBookmarkedDAO() }
}