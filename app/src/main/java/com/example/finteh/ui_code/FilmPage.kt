package com.example.finteh.ui_code

import FilmDesc
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.finteh.models.FilmViewModel

@Composable
fun FilmPageLoading(navController: NavHostController, filmId: Int, filmViewModel: FilmViewModel) {
    val loading: FilmDesc? by filmViewModel.state.collectAsState()
    if (loading == null)
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize(),
        ) {
            CircularProgressIndicator(color = Color.Blue)
        }
    else {
        if (loading!!.kinopoiskId == 0) {
            ErrorPage()
        } else {
            FilmPageInfo(filmViewModel,navController)
        }
    }
}

@Composable
fun FilmPageInfo(filmViewModel: FilmViewModel, navController: NavHostController) {
    val data: FilmDesc? by filmViewModel.state.collectAsState()
    Column(modifier = Modifier.fillMaxSize()){
        Box(modifier = Modifier.padding(bottom = 400.dp)){
            Image(
                painter = rememberAsyncImagePainter(data!!.posterUrl),
                contentDescription = "Изображение"
            )

        }
    }
}
