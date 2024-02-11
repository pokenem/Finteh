package com.example.finteh.ui_code

import Film
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.finteh.navigation.NavigationItem

@Composable
fun FilmTile(film: Film,navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp)
            .shadow(elevation = 10.dp, shape = RoundedCornerShape(15.dp))
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(15.dp))
                .background(Color.White)

        ) {
            Row(
                modifier = Modifier
                    .padding(start = 16.dp, top = 16.dp, bottom = 16.dp)
                    .clickable(
                        onClick = {navController.navigate(
                            "${NavigationItem.FilmDescPage.route}/${film.filmId}"
                        )},
                    )
                    .background(Color(0x00000000)),
            ) {
                Box(
                    modifier = Modifier
                        .height(115.dp)
                        .width(80.dp)
                ) {
                    Image(
                        modifier = Modifier.clip(RoundedCornerShape(5.dp)),
                        painter = rememberAsyncImagePainter(film.posterUrlPreview),
                        contentDescription = "Изображение"
                    )
                };
                Box(
                    modifier = Modifier
                        .height(126.dp)
                        .padding(start = 16.dp, top = 24.dp, bottom = 24.dp, end = 80.dp)
                ) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        Text(
                            overflow = TextOverflow.Ellipsis,
                            text = film.nameRu,
                            fontWeight = FontWeight.W700,
                            fontSize = 22.sp,
                            lineHeight = 16.sp,
                            maxLines = 1,
                            color = Color.Black
                        )
                        Text(
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.padding(top = 16.dp),
                            text = film.genres.joinToString(", ") { it.genre } + " (${film.year})",
                            fontWeight = FontWeight.W500,
                            fontSize = 17.sp,
                            lineHeight = 16.sp,
                            maxLines = 1,
                            color = Color.Black.copy(alpha = 0.6f)
                        )
                    }
                }

            }
        }
    }

}