package com.albertomier.filmlist.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.albertomier.filmlist.domain.model.Film

@Entity(tableName = "films_table")
data class FilmEntity(
    @PrimaryKey
    @ColumnInfo(name = "id") var id: Int,
    @ColumnInfo(name = "originalTitle") var originalTitle: String,
    @ColumnInfo(name = "overview") var overview: String,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "posterPath") var posterPath: String? = "",
    @ColumnInfo(name = "releaseDate") var releaseDate: String,
    @ColumnInfo(name = "voteAverage") var voteAverage: Float,
    @ColumnInfo(name = "fav") var fav: Boolean = true,
    @ColumnInfo(name = "popular") var popular: Boolean = false
)

fun Film.toDatabase() = FilmEntity(id, originalTitle, overview, title, posterPath, releaseDate, voteAverage, fav)