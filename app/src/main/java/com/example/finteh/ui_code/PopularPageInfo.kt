package com.example.finteh.ui_code

import Film
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.finteh.models.FilmListViewModel

@Composable
fun popularPageInfo(filmListViewModel: FilmListViewModel, navController: NavController) {
    val data: List<Film>? by filmListViewModel.state.collectAsState()
    Column(
        modifier = Modifier
            .padding(
                start = 16.dp,
                end = 16.dp,
                bottom = 60.dp,
            )
            .verticalScroll(rememberScrollState())
    ) {
        for (it in data!!) {
            FilmTile(film = it, navController, filmListViewModel)
        }
        Spacer(modifier = Modifier.size(30.dp))

    }
}