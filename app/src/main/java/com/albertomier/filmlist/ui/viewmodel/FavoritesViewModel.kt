package com.albertomier.filmlist.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.albertomier.filmlist.domain.AddFilmToFavorite
import com.albertomier.filmlist.domain.GetFavoritesFilms
import com.albertomier.filmlist.domain.RemoveFilmFromFavorite
import com.albertomier.filmlist.domain.model.Film
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavoritesFilms: GetFavoritesFilms,
    private val addFilmToFavorite: AddFilmToFavorite,
    private val removeFilmFromFavorite: RemoveFilmFromFavorite
) : ViewModel() {

    var listModel = MutableLiveData<List<Film>>()
    val isLoading = MutableLiveData<Boolean>()

    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)

            val result = getFavoritesFilms()

            if (!result.isNullOrEmpty()) {
                listModel.postValue(result)
            } else {
                listModel.postValue(emptyList())
            }

            isLoading.postValue(false)
        }
    }

    fun empty() {
        viewModelScope.launch {
            isLoading.postValue(true)
            listModel.postValue(emptyList())
            isLoading.postValue(false)
        }
    }

    fun deleteFavorite(filmId: Int) {
        viewModelScope.launch {
            removeFilmFromFavorite.invoke(filmId)

            val result = getFavoritesFilms()

            if (!result.isNullOrEmpty()) {
                listModel.postValue(result)
            } else {
                listModel.postValue(emptyList())
            }
        }
    }

    fun addToFavorite(filmId: Int) {
        viewModelScope.launch {
            addFilmToFavorite.invoke(filmId)

            val result = getFavoritesFilms()

            if (!result.isNullOrEmpty()) {
                listModel.postValue(result)
            } else {
                listModel.postValue(emptyList())
            }
        }
    }
}