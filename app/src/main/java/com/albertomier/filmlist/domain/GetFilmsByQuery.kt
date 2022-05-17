package com.albertomier.filmlist.domain

import com.albertomier.filmlist.data.FilmRepository
import com.albertomier.filmlist.data.database.entities.toDatabase
import com.albertomier.filmlist.domain.model.Film
import javax.inject.Inject

class GetFilmsByQuery @Inject constructor(private val repository: FilmRepository) {

    suspend operator fun invoke(query: String): List<Film> {
        val result = repository.getFilmsByQueryFromApi(query)

        return if (result.isNotEmpty()) {
            repository.addFilms(result.map { it.toDatabase() })
            result
        } else {
            repository.getFilmsByQueryFromDatabase(query)
        }
    }
}