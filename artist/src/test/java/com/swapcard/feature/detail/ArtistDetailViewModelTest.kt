package com.swapcard.feature.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.swapcard.aligurelli.core.ArtistDetailQuery
import com.swapcard.aligurelli.core.database.artistbookmark.BookmarkRepository
import com.swapcard.aligurelli.core.database.artistbookmark.BookmarkedArtist
import com.swapcard.aligurelli.core.fragment.ArtistDetailsFragment
import com.swapcard.aligurelli.core.network.repositories.ArtistDetailRepository
import com.swapcard.aligurelli.core.network.responses.ArtistDetail
import com.swapcard.aligurelli.core.utils.NetworkResult
import com.swapcard.feature.rules.CoroutineRule
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ArtistDetailViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutineRule()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @MockK(relaxed = true)
    lateinit var artistDetailRepo: ArtistDetailRepository

    @MockK(relaxed = true)
    lateinit var bookmarkRepo: BookmarkRepository

    @MockK(relaxed = true)
    lateinit var stateObserver: Observer<ArtistDetailViewState>

    @MockK(relaxed = true)
    lateinit var dataObserver: Observer<ArtistDetail>
    lateinit var viewModel: ArtistDetailViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = ArtistDetailViewModel(
            artistDetailRepo = artistDetailRepo,
            bookmarkRepo = bookmarkRepo,
        )
        viewModel.setArtistIDToFetch("TEST_ID")
        viewModel.state.observeForever(stateObserver)
        viewModel.data.observeForever(dataObserver)
    }

    @Test
    fun getArtistDetail_WhenReturnError_NetworkShouldBeErrorState() {
        //given
        coEvery { artistDetailRepo.getArtistDetail(any()) } returns
                flow {
                    emit(
                        NetworkResult.Failed<ArtistDetailQuery.Data>("an error occured")
                    )
                }

        //when
        viewModel.getArtistDetail()
        //then
        val expectedState: ArtistDetailViewState = ArtistDetailViewState.Error
        Assert.assertEquals(expectedState, viewModel.state.value)
        verify { stateObserver.onChanged(expectedState) }


    }

    @Test
    fun getArtistDetail_WhenSuccess_ShouldPostDataResult() {

        //given
        val artistDetailQueryData = mockk<ArtistDetailQuery.Data>()
        val mockNode = mockk<ArtistDetailQuery.Node>()
        val mockFragments = mockk<ArtistDetailQuery.Node.Fragments>()
        val mockArtistDetailsFragments = mockk<ArtistDetailsFragment>()
        val mockRating = mockk<ArtistDetailsFragment.Rating>()
        val bookmarkedArtist = mockk<BookmarkedArtist>()


        every { artistDetailQueryData.node } returns mockNode
        every { artistDetailQueryData.node?.fragments } returns mockFragments
        every { artistDetailQueryData.node?.fragments?.artistDetailsFragment } returns mockArtistDetailsFragments
        every { artistDetailQueryData.node?.fragments?.artistDetailsFragment?.id } returns "ID"
        every { artistDetailQueryData.node?.fragments?.artistDetailsFragment?.name } returns "NAME"
        every { artistDetailQueryData.node?.fragments?.artistDetailsFragment?.disambiguation } returns "Disambiguation"
        every { artistDetailQueryData.node?.fragments?.artistDetailsFragment?.rating } returns mockRating
        every { artistDetailQueryData.node?.fragments?.artistDetailsFragment?.rating?.value } returns 0.0
        every { artistDetailQueryData.node?.fragments?.artistDetailsFragment?.rating?.voteCount } returns 1
        coEvery { bookmarkRepo.getBookmarkedArtistWithID(any()) } returns bookmarkedArtist
        coEvery { artistDetailRepo.getArtistDetail(any()) } returns

                flow {
                    emit(
                        NetworkResult.Success<ArtistDetailQuery.Data>(artistDetailQueryData)
                    )
                }


        //when
        viewModel.getArtistDetail()
        //then
        verify {
            dataObserver.onChanged(
                ArtistDetail(
                    id = artistDetailQueryData.node?.fragments?.artistDetailsFragment?.id!!,
                    name = artistDetailQueryData.node?.fragments?.artistDetailsFragment?.name,
                    disambiguation = artistDetailQueryData.node?.fragments?.artistDetailsFragment?.disambiguation,
                    rating = artistDetailQueryData.node?.fragments?.artistDetailsFragment?.rating?.value
                )
            )
        }
    }
}
