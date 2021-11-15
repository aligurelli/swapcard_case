package com.swapcard.aligurelli.core.database.artistbookmark


class BookmarkedArtistRepository (
    private val bookmarkedArtistDao: BookmarkedArtistDao
) {

    suspend fun getAllBookmarkedArtist(): List<BookmarkedArtist> =
        bookmarkedArtistDao.getAllBookmarkedArtists()


    suspend fun deleteBookmarkedArtist(artist: BookmarkedArtist) =
        bookmarkedArtistDao.deleteBoormarkedArtist(artist)

    suspend fun insertArtistToBookmarks(id: String, name: String, disambiguation : String, type : String) {
        val artistToBookmarked = BookmarkedArtist(
            id = id,
            name = name,
            disambiguation = disambiguation,
            type = type
        )
        bookmarkedArtistDao.insertArtistToBookmarks(artistToBookmarked)
    }
}
