package com.albertomier.filmlist.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.albertomier.filmlist.domain.AddFilmToFavorite
import com.albertomier.filmlist.domain.GetFilmsByQuery
import com.albertomier.filmlist.domain.GetPopularFilms
import com.albertomier.filmlist.domain.RemoveFilmFromFavorite
import com.albertomier.filmlist.domain.model.Film
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ListViewModelTest {

    @RelaxedMockK
    private lateinit var getPopularFilms: GetPopularFilms

    @RelaxedMockK
    private lateinit var getFilmsByQuery: GetFilmsByQuery

    @RelaxedMockK
    private lateinit var addFilmToFavorite: AddFilmToFavorite

    @RelaxedMockK
    private lateinit var removeFilmFromFavorite: RemoveFilmFromFavorite

    private lateinit var listViewModel: ListViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        listViewModel =
            ListViewModel(
                getPopularFilms,
                getFilmsByQuery,
                addFilmToFavorite,
                removeFilmFromFavorite
            )

        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when viewmodel is created the first time, get popular films`() = runTest {
        //Given
        val film1 = Film(1, "iron man", "", "", "", "", 0f, false)
        val film2 = Film(2, "iron man 2", "", "", "", "", 0f, false)
        val myList = listOf(film1, film2)

        coEvery { getPopularFilms() } returns myList

        //When
        listViewModel.onCreate()

        //Then
        assert(listViewModel.listModel.value == myList)
    }

    @Test
    fun `when byQuery return films set on the livedata`() = runTest {
        //Given
        val film1 = Film(1, "iron man", "", "", "", "", 0f, false)
        val film2 = Film(2, "iron man 2", "", "", "", "", 0f, false)
        val myList = listOf(film1, film2)

        coEvery { getFilmsByQuery("iron") } returns myList

        //When
        listViewModel.byQuery("iron")

        //Then
        assert(listViewModel.listModel.value == myList)
    }

    @Test
    fun `if byQuery return null return empty list`() = runTest {
        //Given
        coEvery { getFilmsByQuery("iron") } returns emptyList()

        //When
        listViewModel.byQuery("iron")

        //Then
        assert(listViewModel.listModel.value != null)
    }
}