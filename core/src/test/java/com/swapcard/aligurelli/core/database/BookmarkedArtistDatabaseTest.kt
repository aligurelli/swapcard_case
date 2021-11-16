
package com.swapcard.aligurelli.core.database

import com.swapcard.aligurelli.core.database.artistbookmark.BookmarkedArtistDao
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test

class BookmarkedArtistDatabaseTest {

    @MockK
    lateinit var bookmarkDB: BookmarkedArtistDB

    @MockK
    lateinit var bookmarkDAO: BookmarkedArtistDao

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun obtainBookmarkedArtistDao() {
        every { bookmarkDB.artistBookmarkedDAO() } returns bookmarkDAO

        assertThat(
            bookmarkDB.artistBookmarkedDAO(),
            instanceOf(BookmarkedArtistDao::class.java)
        )
    }
}
