package com.albertomier.filmlist.data.model

import com.google.gson.annotations.SerializedName

data class FilmModel(
    @SerializedName("id") var id: Int,
    @SerializedName("original_title") var originalTitle: String,
    @SerializedName("overview") var overview: String,
    @SerializedName("title") var title: String,
    @SerializedName("poster_path") var posterPath: String,
    @SerializedName("release_date") var releaseDate: String,
    @SerializedName("vote_average") var voteAverage: Float,
    var fav: Boolean = false
)