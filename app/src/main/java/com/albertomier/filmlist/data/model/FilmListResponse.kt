package com.albertomier.filmlist.data.model

import com.google.gson.annotations.SerializedName

data class FilmListResponse(
    @SerializedName("page") var page: Int,
    @SerializedName("results") var results: List<FilmModel>
)