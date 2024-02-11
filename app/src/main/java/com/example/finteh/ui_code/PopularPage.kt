package com.example.finteh.ui_code

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.finteh.models.FilmListViewModel

@Composable
fun popularPage(navController: NavController, filmListViewModel: FilmListViewModel) {

    Scaffold(backgroundColor = Color.White, topBar = {
        TopAppBar(
            elevation = 0.dp,
            backgroundColor = Color.White,
            // navigationIcon = { Icon(Icons.Filled.Delete, contentDescription = "") },
            title = {
                Text(
                    text = "Популярные",
                    fontWeight = FontWeight.W600,
                    fontSize = 25.sp,
                    lineHeight = 16.sp,
                    maxLines = 1,
                )
            },
        )
    }) { loadingPage(it, filmListViewModel,navController) }
}
