package com.albertomier.filmlist.domain

import com.albertomier.filmlist.data.FilmRepository
import com.albertomier.filmlist.data.database.entities.toDatabase
import com.albertomier.filmlist.domain.model.Film
import javax.inject.Inject

class GetPopularFilms @Inject constructor(private val repository: FilmRepository) {

    suspend operator fun invoke(): List<Film> {
        val popularFIlms = repository.getPopularFilmFromApi()

        if (popularFIlms.isNotEmpty()) {
            val result = popularFIlms.map { it.toDatabase() }
            result.map {
                it.popular = true
            }
            repository.addFilms(result)
        }
        return repository.getPopularFilmFromDatabase()
    }
}