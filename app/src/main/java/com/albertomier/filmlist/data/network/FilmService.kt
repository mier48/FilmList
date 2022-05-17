package com.albertomier.filmlist.data.network

import com.albertomier.filmlist.data.model.FilmListResponse
import com.albertomier.filmlist.data.model.FilmModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class FilmService @Inject constructor(private val api: FilmApiClient) {

    private val apiKey = "bb50e5acab34b3fb7d5232f5fb65f334"

    suspend fun getPopularFilms(): List<FilmModel> {
        return withContext(Dispatchers.IO) {
            val response: Response<FilmListResponse> = api.getPopularFilms(apiKey, "es", 1)
            response.body()?.results ?: emptyList()
        }
    }

    suspend fun getFilmById(id: Int): FilmModel {
        return withContext(Dispatchers.IO) {
            api.getFilmById(id, apiKey, "es")
        }
    }

    suspend fun getFilmsByQuery(query: String): List<FilmModel> {
        return withContext(Dispatchers.IO) {
            val response: Response<FilmListResponse> = api.getFilmsByQuery(apiKey, "es", 1, query)
            response.body()?.results ?: emptyList()
        }
    }
}