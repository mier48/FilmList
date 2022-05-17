package com.albertomier.filmlist.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.albertomier.filmlist.domain.AddFilmToFavorite
import com.albertomier.filmlist.domain.GetFilmsByQuery
import com.albertomier.filmlist.domain.GetPopularFilms
import com.albertomier.filmlist.domain.RemoveFilmFromFavorite
import com.albertomier.filmlist.domain.model.Film
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val getPopularFilms: GetPopularFilms,
    private val getFilmsByQuery: GetFilmsByQuery,
    private val addFilmToFavorite: AddFilmToFavorite,
    private val removeFilmFromFavorite: RemoveFilmFromFavorite
) : ViewModel() {

    var listModel = MutableLiveData<List<Film>>()
    val isLoading = MutableLiveData<Boolean>()
    private var allItems: List<Film> = ArrayList()

    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)

            val result = getPopularFilms()
            allItems = result

            if (!result.isNullOrEmpty()) {
                listModel.postValue(result)
            } else {
                listModel.postValue(emptyList())
            }

            isLoading.postValue(false)
        }
    }

    fun byQuery(query: String) {
        viewModelScope.launch {
            isLoading.postValue(true)

            val result = getFilmsByQuery(query)
            allItems = result

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
            allItems = emptyList()
            isLoading.postValue(false)
        }
    }

    fun deleteFavorite(filmId: Int) {
        viewModelScope.launch {
            removeFilmFromFavorite.invoke(filmId)

            allItems.map {
                if (it.id == filmId) {
                    it.fav = false
                }
            }

            listModel.postValue(allItems)
        }
    }

    fun addToFavorite(filmId: Int) {
        viewModelScope.launch {
            addFilmToFavorite.invoke(filmId)

            allItems.map {
                if (it.id == filmId) {
                    it.fav = true
                }
            }

            listModel.postValue(allItems)
        }
    }
}