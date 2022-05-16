package com.albertomier.filmlist.domain.model

import com.albertomier.filmlist.data.database.entities.FilmEntity
import com.albertomier.filmlist.data.model.FilmModel

data class Film(
    val id: Int,
    val originalTitle: String,
    val overview: String,
    val title: String,
    val posterPath: String,
    val releaseDate: String,
    val voteAverage: Float,
    var fav: Boolean
)

fun FilmModel.toDomain() = Film(id, originalTitle, overview, title, posterPath, releaseDate, voteAverage, fav)
fun FilmEntity.toDomain() = Film(id, originalTitle, overview, title, posterPath, releaseDate, voteAverage, fav)