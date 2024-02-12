package com.example.finteh.ui_code

import androidx.compose.ui.res.vectorResource
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.example.finteh.R
import com.example.finteh.models.FilmListViewModel
import com.example.finteh.models.FilmViewModel


@Composable
fun ErrorPage(viewModel: ViewModel, id: Int? = null) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize(),

        ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 40.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                imageVector = ImageVector.vectorResource(R.drawable.ic_cloud),
                contentDescription = "icon",
            )
            Spacer(modifier = Modifier.size(10.dp))

            Text(
                text = "Произошла ошибка при загрузке данных, проверьте подключение к сети",
                fontSize = 16.sp,
                lineHeight = 16.sp,
                fontWeight = FontWeight.W400,
                color = Color(0xFF0094FF),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.size(40.dp))
            Button(
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF0094FF)),
                onClick = {
                    when (viewModel) {
                        is FilmViewModel -> {
                            viewModel.getFilmById(id!!)
                        }
                        is FilmListViewModel -> {
                            viewModel.getFilms()
                        }
                    }
                },
                modifier = Modifier
                    .clip(RoundedCornerShape(50.dp))
                    .background(Color(0xFF0094FF)),
                elevation = ButtonDefaults.elevation(0.dp),

                ) {
                Text(
                    text = "Повторить",
                    fontSize = 16.sp,
                    lineHeight = 16.sp,
                    fontWeight = FontWeight.W500,
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
            }
        }

    }
}