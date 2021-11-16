package com.swapcard.aligurelli.core.database.artistbookmark


class BookmarkRepository (
    private val bookmarkedArtistDao: BookmarkedArtistDao
) {

    suspend fun getAllBookmarkedArtist(): List<BookmarkedArtist> =
        bookmarkedArtistDao.getAllBookmarkedArtists()


    suspend fun deleteBookmarkedArtist(artist: BookmarkedArtist) =
        bookmarkedArtistDao.deleteBoormarkedArtist(artist)


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
}
