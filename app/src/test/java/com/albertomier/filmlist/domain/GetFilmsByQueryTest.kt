package com.albertomier.filmlist.domain

import com.albertomier.filmlist.data.FilmRepository
import com.albertomier.filmlist.domain.model.Film
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetFilmsByQueryTest {

    @RelaxedMockK
    private lateinit var repository: FilmRepository

    lateinit var getFilmsByQuery: GetFilmsByQuery

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getFilmsByQuery = GetFilmsByQuery(repository)
    }

    @Test
    fun `when the user write some text then return filtered data`() = runBlocking {
        //Given
        val myList = listOf(Film(1, "iron man", "", "", "", "", 0f, false))
        coEvery { repository.getFilmsByQueryFromApi("iron man") } returns myList

        //When
        val result = getFilmsByQuery("iron man")

        //Then
        assert(myList == result)
    }
}