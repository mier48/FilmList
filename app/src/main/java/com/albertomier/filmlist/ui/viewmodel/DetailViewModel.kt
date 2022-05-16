package com.albertomier.filmlist.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.albertomier.filmlist.domain.GetFilmById
import com.albertomier.filmlist.domain.model.Film
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getFilmById: GetFilmById
) : ViewModel() {

    var dataModel = MutableLiveData<Film>()
    val isLoading = MutableLiveData<Boolean>()

    fun onCreate(id: Int) {
        viewModelScope.launch {
            isLoading.postValue(true)

            val result = getFilmById(id)
            dataModel.postValue(result)

            isLoading.postValue(false)
        }
    }
}