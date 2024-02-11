package com.example.finteh.ui_code

import FilmDesc
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.modifier.modifierLocalProvider
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
            ErrorPage(filmViewModel, filmId)
        } else {
            FilmPageInfo(filmViewModel, navController)
        }
    }
}

@Composable
fun FilmPageInfo(filmViewModel: FilmViewModel, navController: NavHostController) {
    val data: FilmDesc? by filmViewModel.state.collectAsState()
    Column {
        Box() {
            Image(
                painter = rememberAsyncImagePainter(
                    data!!.posterUrl,
                    contentScale = ContentScale.FillWidth
                ),
                contentDescription = "Изображение",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxWidth(),
                alignment = Alignment.TopCenter
            )
        }
        Box(modifier = Modifier
            .padding(horizontal = 40.dp)
            .fillMaxSize()) {
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                Spacer(modifier = Modifier.size(24.dp))
                Text(
                    text = data!!.nameRu,
                    fontWeight = FontWeight.W600,
                    fontSize = 22.sp,
                    lineHeight = 16.sp,
                )
                Spacer(modifier = Modifier.size(16.dp))
                Text(
                    text = data!!.description,
                    fontWeight = FontWeight.W400,
                    fontSize = 16.sp,
                    lineHeight = 16.sp,
                    color = Color.Black.copy(alpha = 0.6f)
                )
                Spacer(modifier = Modifier.size(16.dp))
                Row {
                    Text(
                        text = "Жанры: ",
                        fontWeight = FontWeight.W500,
                        fontSize = 16.sp,
                        lineHeight = 16.sp,
                        color = Color.Black.copy(alpha = 0.6f),
                    )
                    Text(
                        text = data!!.genres.joinToString(", ") { it.genre },
                        fontWeight = FontWeight.W400,
                        fontSize = 16.sp,
                        lineHeight = 16.sp,
                        color = Color.Black.copy(alpha = 0.6f),
                    )
                }
                Spacer(modifier = Modifier.size(8.dp))
                Row {
                    Text(
                        text = "Страны: ",
                        fontWeight = FontWeight.W500,
                        fontSize = 16.sp,
                        lineHeight = 16.sp,
                        color = Color.Black.copy(alpha = 0.6f),
                    )
                    Text(
                        text = data!!.countries.joinToString(", ") { it.country },
                        fontWeight = FontWeight.W400,
                        fontSize = 16.sp,
                        lineHeight = 16.sp,
                        color = Color.Black.copy(alpha = 0.6f),
                    )
                }
                Spacer(modifier = Modifier.size(48.dp))
            }
        }

    }
}
