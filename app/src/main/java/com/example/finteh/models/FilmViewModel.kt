package com.example.finteh.models

import FilmDesc
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finteh.api.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class FilmViewModel(id: Int) : ViewModel() {

    private val filmRepository: FilmRepository = FilmRepository(ApiClient())

    val state: MutableStateFlow<FilmDesc?> = MutableStateFlow(null)

    init {
        getFilmById(id)
    }

    /* val coroutineExceptionHandler = CoroutineExceptionHandler{_, throwable ->
         throwable.printStackTrace()
     }*/
    fun getFilmById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val films = filmRepository.getFilmById(id)
            state.value = films
        }
    }
}