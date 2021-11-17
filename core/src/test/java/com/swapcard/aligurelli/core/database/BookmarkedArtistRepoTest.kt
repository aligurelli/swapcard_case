package com.swapcard.aligurelli.core.database

import com.swapcard.aligurelli.core.database.artistbookmark.BookmarkRepository
import com.swapcard.aligurelli.core.database.artistbookmark.BookmarkedArtist
import com.swapcard.aligurelli.core.database.artistbookmark.BookmarkedArtistDao
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.slot
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class BookmarkedArtistRepoTest {

    @MockK(relaxed = true)
    lateinit var bookmarkedArtistDao: BookmarkedArtistDao
    lateinit var bookmarkedArtistRepo: BookmarkRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        bookmarkedArtistRepo = BookmarkRepository(bookmarkedArtistDao)
    }



    @Test
    fun getAllBookmarkedArtist_ShouldInvokeCorrectDaoMethod() {
        runBlocking {
            bookmarkedArtistRepo.getAllBookmarkedArtist()

            coVerify { bookmarkedArtistDao.getAllBookmarkedArtists() }
        }
    }


    @Test
    fun insertArtistToBookmarked_ShouldInvokeCorrectDaoMethod() = runBlocking {
        val artistToInsert = BookmarkedArtist(
            "test_ID",
            "TEST_NAME",
            "TEST_DISAMBIGUATION"
        )

        val artist = slot<BookmarkedArtist>()
        bookmarkedArtistRepo.insertArtistToBookmarks(
            id = artistToInsert.id,
            name = artistToInsert.name,
            disambiguation = artistToInsert.disambiguation
        )

        coVerify { bookmarkedArtistDao.insertArtistToBookmarks(capture(artist)) }
        assertEquals(artistToInsert, artist.captured)
    }



}
