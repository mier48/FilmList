package com.albertomier.filmlist.domain

import com.albertomier.filmlist.data.FilmRepository
import com.albertomier.filmlist.domain.model.Film
import javax.inject.Inject

class GetFilmById @Inject constructor(private val repository: FilmRepository) {

    suspend operator fun invoke(id: Int): Film {
        val result = repository.getFilmByIdFromApi(id)
        val database = repository.getFilmByIdFromDatabase(id)

        return if (result != null) {
            result.fav = database.fav
            result
        } else {
            database
        }
    }
}