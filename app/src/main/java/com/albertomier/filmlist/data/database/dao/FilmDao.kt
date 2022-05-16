package com.albertomier.filmlist.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.albertomier.filmlist.data.database.entities.FilmEntity

@Dao
interface FilmDao {
    @Query("SELECT * FROM films_table ORDER BY id ASC")
    suspend fun getAllFilms(): List<FilmEntity>

    @Query("SELECT * FROM films_table WHERE id = :filmId")
    suspend fun getFilmById(filmId: Int): FilmEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(beer: FilmEntity)

    @Query("DELETE FROM films_table WHERE id = :filmId")
    suspend fun remove(filmId: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(quotes: List<FilmEntity>)

    @Query("DELETE FROM films_table")
    suspend fun deleteAll()
}