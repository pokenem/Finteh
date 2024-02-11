package com.example.finteh.models

import Film
import android.content.Context
import android.net.ConnectivityManager
import androidx.compose.ui.text.toLowerCase
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finteh.models.FilmRepository
import com.example.finteh.api.ApiClient
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import okhttp3.internal.notifyAll

class FilmListViewModel : ViewModel() {

    private val filmRepository: FilmRepository = FilmRepository(ApiClient())

    val state: MutableStateFlow<List<Film>?> = MutableStateFlow(null)
    val stateIsSearch: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val stateSearchStr: MutableStateFlow<String> = MutableStateFlow("")

    var filmList: List<Film>? = emptyList()

    init {
        getFilms()
    }

    fun syncLists() {
        filmList = state.value
    }

    fun getFilms() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val films = filmRepository.getFilms()
                state.value = films
                filmList = films
            } catch (e: Exception) {
                state.value = emptyList()
            }
        }
    }

    fun addFavourite(filmId: Int) {
        filmList = filmList!!.map { film ->
            if (film.filmId == filmId) {
                film.copy(favourite = !film.favourite)
            } else {
                film
            }
        }
        state.value = state.value!!.map { film ->
            if (film.filmId == filmId) {
                film.copy(favourite = !film.favourite)
            } else {
                film
            }
        }
    }

    fun showFavourite(){
        var list: List<Film>?
        state.value = state.value!!.filter{ film ->
            film.favourite
        }
    }

    fun backToPopular(){
        state.value = filmList
    }

    fun SearchFilm(str: String){
        backToPopular()
        stateSearchStr.value = str
        state.value = state.value!!.filter{film ->
            film.nameRu!!.contains(str,ignoreCase = true)
        }
    }

    fun changeSearchState(){
        stateIsSearch.value = !stateIsSearch.value
        stateSearchStr.value = ""
        backToPopular()
    }



}