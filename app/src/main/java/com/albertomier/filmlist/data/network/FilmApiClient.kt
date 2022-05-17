package com.albertomier.filmlist.data.network

import com.albertomier.filmlist.data.model.FilmListResponse
import com.albertomier.filmlist.data.model.FilmModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FilmApiClient {

    @GET("movie/popular")
    suspend fun getPopularFilms(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int,
    ): Response<FilmListResponse>

    @GET("movie/{movie_id}")
    suspend fun getFilmById(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") api_key: String,
        @Query("language") language: String
    ): FilmModel

    @GET("search/movie")
    suspend fun getFilmsByQuery(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("page") page: Int,
        @Query("query") query: String
    ): Response<FilmListResponse>
}