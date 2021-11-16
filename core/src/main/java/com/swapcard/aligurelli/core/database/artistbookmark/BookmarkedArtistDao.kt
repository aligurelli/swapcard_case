package com.swapcard.aligurelli.core.database.artistbookmark

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface BookmarkedArtistDao {


    @Query("SELECT * FROM artist_bookmarked")
    suspend fun getAllBookmarkedArtists(): List<BookmarkedArtist>


    @Delete
    suspend fun deleteBoormarkedArtist(artist: BookmarkedArtist)

    @Insert
    suspend fun insertArtistToBookmarks(artist: BookmarkedArtist)


    @Query("SELECT * FROM artist_bookmarked WHERE id = :artistID")
    suspend fun getBookmarkedArtistWithID(artistID: String): BookmarkedArtist?


}
