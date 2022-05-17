package com.albertomier.filmlist.domain

import com.albertomier.filmlist.data.FilmRepository
import com.albertomier.filmlist.domain.model.Film
import javax.inject.Inject

class GetFavoritesFilms @Inject constructor(private val repository: FilmRepository) {

    suspend operator fun invoke(): List<Film> {
        return repository.getFavoritesFilms()
    }
}