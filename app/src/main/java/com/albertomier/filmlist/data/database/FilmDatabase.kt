package com.albertomier.filmlist.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.albertomier.filmlist.data.database.dao.FilmDao
import com.albertomier.filmlist.data.database.entities.FilmEntity

@Database(entities = [FilmEntity::class], version = 1)
abstract class FilmDatabase : RoomDatabase() {
    abstract fun getFilmDao(): FilmDao
}