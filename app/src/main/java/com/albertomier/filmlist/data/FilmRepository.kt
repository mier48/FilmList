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

    suspend fun getFilmsByQueryFromApi(query: String): List<Film> {
        val response: List<FilmModel> = api.getFilmsByQuery(query)
        return response.map { it.toDomain() }
    }

    suspend fun getFilmsByQueryFromDatabase(query: String): List<Film> {
        val response: List<FilmEntity> = filmDao.getFilmsByQuery("%${query}%")
        return response.map { it.toDomain() }
    }

    suspend fun getPopularFilmFromDatabase(): List<Film> {
        val response: List<FilmEntity> = filmDao.getPopularFilms()
        return response.map { it.toDomain() }
    }

    suspend fun getFilmByIdFromDatabase(id: Int): Film {
        return filmDao.getFilmById(id).toDomain()
    }

    suspend fun getFilmsFromDatabase(): List<Film> {
        val response = filmDao.getAllFilms()
        return response.map { it.toDomain() }
    }

    suspend fun getFavoritesFilms(): List<Film> {
        val response = filmDao.getFavoritesFilms()
        return response.map { it.toDomain() }
    }

    suspend fun getFavoriteFilm(id: Int): Film {
        val response = filmDao.getFavoriteFilm(id)
        return response.toDomain()
    }

    suspend fun addFilms(films: List<FilmEntity>) {
        filmDao.insertAll(films)
    }

    suspend fun addFilm(film: FilmEntity) {
        filmDao.insert(film)
    }

    suspend fun deleteAll() {
        filmDao.deleteAll()
    }

    suspend fun deleteFilmFromFavorite(filmId: Int) {
        filmDao.deleteFilmFromFavorite(filmId)
    }

    suspend fun addFilmToFavorite(filmId: Int) {
        filmDao.addFilmToFavorite(filmId)
    }
}