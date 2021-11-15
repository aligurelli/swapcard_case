
package com.swapcard.aligurelli.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.swapcard.aligurelli.core.BuildConfig
import com.swapcard.aligurelli.core.database.artistbookmark.BookmarkedArtist
import com.swapcard.aligurelli.core.database.artistbookmark.BookmarkedArtistDao


@Database(
    entities = [BookmarkedArtist::class],
    version = BuildConfig.SWAPCARD_DATABASE_VERSION,

)
abstract class BookmarkedArtistDB : RoomDatabase() {
    
    abstract fun artistBookmarkedDAO(): BookmarkedArtistDao
}
