package com.albertomier.filmlist.domain

import com.albertomier.filmlist.data.FilmRepository
import com.albertomier.filmlist.domain.model.Film
import javax.inject.Inject

class GetFilmById @Inject constructor(private val repository: FilmRepository) {

    suspend operator fun invoke(id: Int): Film {
        var result = repository.getFilmByIdFromApi(id)

        if (result == null) {
            result = repository.getFilmByIdFromDatabase(id)
        }

        return result
    }
}