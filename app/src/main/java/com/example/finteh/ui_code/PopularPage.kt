package com.example.finteh.ui_code

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.finteh.models.FilmListViewModel
import com.example.finteh.models.FilmViewModel

@Composable
fun popularPage(navController: NavController, filmListViewModel: FilmListViewModel) {

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
        .padding(top = 30.dp)) {
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
                        color = Color.Black
                    )
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            Icons.Default.Search,
                            contentDescription = "Search",
                            modifier = Modifier,
                            tint = Color(0xFF0094FF),
                        )
                    }
                }
            )

        }, bottomBar = {
            BottomAppBar(modifier = Modifier, backgroundColor = Color.White, contentPadding = PaddingValues(vertical = 20.dp)){
                Row(modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(horizontal = 16.dp), horizontalArrangement = Arrangement.Center) {
                    Button(
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFDEEFFF)),
                        onClick = {},
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(50.dp))
                            .background(Color(0xFFDEEFFF)),
                        elevation = ButtonDefaults.elevation(0.dp),

                    ) {
                        Text(
                            text = "Популярные",
                            fontSize = 14.sp,
                            lineHeight = 16.sp,
                            fontWeight = FontWeight.W500,
                            textAlign = TextAlign.Center,
                            color = Color(0xFF0094FF)
                        )
                    }
                    Spacer(modifier = Modifier.size(60.dp))
                    Button(
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF0094FF)),
                        onClick = {},
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(50.dp))
                            .background(Color(0xFF0094FF)),
                        elevation = ButtonDefaults.elevation(0.dp),

                    ) {
                        Text(
                            text = "Избранное",
                            fontSize = 14.sp,
                            lineHeight = 16.sp,
                            fontWeight = FontWeight.W500,
                            textAlign = TextAlign.Center,
                            color = Color.White
                        )
                    }
                }

            }
        }) { loadingPage(it, filmListViewModel, navController) }
    }
}
