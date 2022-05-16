package com.albertomier.filmlist.data

import com.albertomier.filmlist.data.database.dao.FilmDao
import com.albertomier.filmlist.data.database.entities.FilmEntity
import com.albertomier.filmlist.data.model.FilmModel
import com.albertomier.filmlist.data.network.FilmService
import com.albertomier.filmlist.domain.model.Film
import com.albertomier.filmlist.domain.model.toDomain
import javax.inject.Inject

class FilmRepository @Inject constructor(
    private val api: FilmService,
    private val filmDao: FilmDao
) {

    suspend fun getPopularFilmFromApi(): List<Film> {
        val response: List<FilmModel> = api.getPopularFilms()

        return response.map { it.toDomain() }
    }

    suspend fun getFilmByIdFromApi(id: Int): Film? {
        return try {
            val response: FilmModel = api.getFilmById(id)
            response.toDomain()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    suspend fun getFilmByIdFromDatabase(id: Int): Film {
        return filmDao.getFilmById(id).toDomain()
    }

    suspend fun addPopularFilm(films: List<FilmEntity>) {
        filmDao.insertAll(films)
    }

    suspend fun deleteAll() {
        filmDao.deleteAll()
    }
}