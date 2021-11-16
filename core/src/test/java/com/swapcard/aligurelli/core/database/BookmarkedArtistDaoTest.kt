package com.swapcard.aligurelli.core.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.swapcard.aligurelli.core.TestRobolectric
import com.swapcard.aligurelli.core.database.artistbookmark.BookmarkedArtist
import com.swapcard.aligurelli.core.database.artistbookmark.BookmarkedArtistDao
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class BookmarkedArtistDaoTest : TestRobolectric(){

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: BookmarkedArtistDB
    private lateinit var bookmarkedArtistDao: BookmarkedArtistDao
    private val fakeBookmarkedArtists = listOf(
        BookmarkedArtist("TEST_1", "ELVIS", "D1"),
        BookmarkedArtist("TEST_2", "JEFF BUCKLEY", "D2"),
        BookmarkedArtist("TEST_3", "FREDY MERCURY", "D3")
    )

    @Before
    fun setUp() = runBlocking {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room
            .inMemoryDatabaseBuilder(context, BookmarkedArtistDB::class.java)
            .allowMainThreadQueries()
            .build()

        bookmarkedArtistDao = database.artistBookmarkedDAO()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun obtainAllBookmarkedArtist_WithoutData_ShouldReturnEmpty() = runBlocking {
        assertTrue(bookmarkedArtistDao.getAllBookmarkedArtists().isNullOrEmpty())
    }

    @Test
    fun obtainAllBookmarkedArtist_WithData_ShouldReturn() = runBlocking {
        bookmarkedArtistDao.insertArtistsToBookmarks(fakeBookmarkedArtists)

        assertEquals(fakeBookmarkedArtists, bookmarkedArtistDao.getAllBookmarkedArtists())
    }

    @Test
    fun obtainBookmarkedArtistById_WithoutData_ShouldNotFound() = runBlocking {
        val alreadyBookmarkedArtistToFound = fakeBookmarkedArtists.first()

        assertNull(bookmarkedArtistDao.getBookmarkedArtistWithID(alreadyBookmarkedArtistToFound.id))
    }

}
