package com.albertomier.filmlist.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.albertomier.filmlist.domain.GetPopularFilms
import com.albertomier.filmlist.domain.model.Film
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getPopularFilms: GetPopularFilms
) : ViewModel() {

    var listModel = MutableLiveData<List<Film>>()
    val isLoading = MutableLiveData<Boolean>()

    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)

            val result = getPopularFilms()

            if (!result.isNullOrEmpty()) {
                listModel.postValue(result)
            } else {
                listModel.postValue(emptyList())
            }

            isLoading.postValue(false)
        }
    }
}