package com.albertomier.filmlist.domain

import com.albertomier.filmlist.data.FilmRepository
import com.albertomier.filmlist.data.database.entities.toDatabase
import com.albertomier.filmlist.domain.model.Film
import javax.inject.Inject

class RemoveFilmFromFavorite @Inject constructor(private val repository: FilmRepository) {

    suspend operator fun invoke(filmId: Int) {
        return repository.deleteFilmFromFavorite(filmId)
    }
}