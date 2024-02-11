package com.example.finteh.models

import Film
import android.content.Context
import android.net.ConnectivityManager
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finteh.models.FilmRepository
import com.example.finteh.api.ApiClient
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class FilmListViewModel : ViewModel() {

    private val filmRepository: FilmRepository = FilmRepository(ApiClient())

    val state: MutableStateFlow<List<Film>?> = MutableStateFlow(null)

    init {
        getFilms()
    }

    fun getFilms() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val films = filmRepository.getFilms()
                state.value = films
            } catch (e: Exception) {
                state.value = emptyList()
            }
        }
    }
}