package com.example.finteh.models

import Country
import FilmDesc
import Genre
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
            try {
                val films = filmRepository.getFilmById(id)
                state.value = films
            } catch (e: Exception) {
                state.value = FilmDesc(
                    kinopoiskId = 0,
                    nameRu = "Абоба",
                    year = "2022",
                    posterUrl = "data:image/jpeg;base64,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",
                    genres = listOf(Genre("крута")),
                    description = "вообще круто",
                    countries = listOf(Country("зимбабве")),
                )
            }
        }
    }
}