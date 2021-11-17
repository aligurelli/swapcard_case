package com.swapcard.aligurelli.core.database.artistbookmark

import org.jetbrains.annotations.TestOnly


class BookmarkRepository (
    private val bookmarkedArtistDao: BookmarkedArtistDao
) {

    suspend fun getAllBookmarkedArtist(): List<BookmarkedArtist> =
        bookmarkedArtistDao.getAllBookmarkedArtists()

    suspend fun getBookmarkedArtistWithID(artistID: String): BookmarkedArtist? =
        bookmarkedArtistDao.getBookmarkedArtistWithID(artistID)

    suspend fun insertArtistToBookmarks(id: String, name: String, disambiguation : String) {
        val artistToBookmarked = BookmarkedArtist(
            id = id,
            name = name,
            disambiguation = disambiguation,
        )
        bookmarkedArtistDao.insertArtistToBookmarks(artistToBookmarked)
    }


    @TestOnly
    suspend fun insertArtistsToBookmarked(artists: List<BookmarkedArtist>) =
        bookmarkedArtistDao.insertArtistsToBookmarks(artists)
}
