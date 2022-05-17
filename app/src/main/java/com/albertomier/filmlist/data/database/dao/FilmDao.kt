package com.albertomier.filmlist.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.albertomier.filmlist.data.database.entities.FilmEntity

@Dao
interface FilmDao {
    @Query("SELECT * FROM films_table")
    suspend fun getAllFilms(): List<FilmEntity>

    @Query("SELECT * FROM films_table WHERE popular = 1")
    suspend fun getPopularFilms(): List<FilmEntity>

    @Query("SELECT * FROM films_table WHERE title LIKE :query")
    suspend fun getFilmsByQuery(query: String): List<FilmEntity>

    @Query("SELECT * FROM films_table WHERE id = :filmId")
    suspend fun getFilmById(filmId: Int): FilmEntity

    @Query("SELECT * FROM films_table WHERE fav = 1")
    suspend fun getFavoritesFilms(): List<FilmEntity>

    @Query("SELECT * FROM films_table WHERE fav = 1 AND id = :filmId")
    suspend fun getFavoriteFilm(filmId: Int): FilmEntity

    @Query("UPDATE films_table SET fav = 0 WHERE id = :filmId")
    suspend fun deleteFilmFromFavorite(filmId: Int)

    @Query("UPDATE films_table SET fav = 1 WHERE id = :filmId")
    suspend fun addFilmToFavorite(filmId: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(beer: FilmEntity)

    @Query("DELETE FROM films_table WHERE id = :filmId")
    suspend fun remove(filmId: Int)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(quotes: List<FilmEntity>)

    @Query("DELETE FROM films_table")
    suspend fun deleteAll()
}