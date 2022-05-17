package com.albertomier.filmlist.domain

import com.albertomier.filmlist.data.FilmRepository
import com.albertomier.filmlist.domain.model.Film
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetPopularFilmsTest {

    @RelaxedMockK
    private lateinit var repository: FilmRepository

    lateinit var getPopularFilms: GetPopularFilms

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getPopularFilms = GetPopularFilms(repository)
    }

    @Test
    fun `when the api return something insert in database and get values from database`() = runBlocking {
        //
        val myList = listOf(Film(1, "Titulo", "", "", "", "", 0f, false))
        coEvery { repository.getPopularFilmFromApi() } returns myList

        //When
        getPopularFilms()

        //Then
        coVerify(exactly = 1) { repository.addFilms(any()) }
        coVerify(exactly = 1) { repository.getPopularFilmFromDatabase() }
    }
}
