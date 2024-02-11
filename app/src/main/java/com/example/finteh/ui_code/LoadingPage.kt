package com.example.finteh.ui_code

import Film
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.finteh.models.FilmListViewModel

@Composable
fun loadingPage(paddingValues: PaddingValues, filmListViewModel: FilmListViewModel, navController: NavController) {
    val loading: List<Film>? by filmListViewModel.state.collectAsState()
    if (loading == null)
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize(),
        ) {
            CircularProgressIndicator(color = Color.Blue)
        }
    else {
        if (loading!!.isEmpty()) {
            ErrorPage(filmListViewModel)
        } else {
            popularPageInfo(filmListViewModel,navController)
        }
    }
}
