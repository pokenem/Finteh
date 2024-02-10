package com.example.finteh

import ApiClient
import Film
import FilmsTop
import android.graphics.Insets
import android.os.Bundle
import android.text.Layout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.finteh.ui.theme.FintehTheme

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Response


class FilmListViewModel : ViewModel() {

    private val filmRepository: FilmRepository = FilmRepository(ApiClient())

    val state: MutableStateFlow<List<Film>?> = MutableStateFlow(null)

    init {
        getFilms()
    }

    fun getFilms() {
        viewModelScope.launch(Dispatchers.IO) {
            val films = filmRepository.getFilms()
            state.value = films
        }
    }
}

class FilmRepository(private val apiClient: ApiClient) {
    suspend fun getFilms(): List<Film> {
        val response: Response<FilmsTop> = apiClient.apiService.getTop100()
        if (response.isSuccessful) {
            if (response.body() != null) {
                return response.body()!!.films
            } else {
                return emptyList<Film>()
            }
        } else {
            val errorBody: String? = response.errorBody()?.string()
            return emptyList<Film>()
        }
    }
}


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            popularPage();
        }
    }
}

@Composable
fun popularPage(filmListViewModel: FilmListViewModel = viewModel()) {
    FintehTheme {
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
        }) { loadingPage(it, filmListViewModel) }
    }
}


@Composable
fun loadingPage(paddingValues: PaddingValues, filmListViewModel: FilmListViewModel) {
    val loading: List<Film>? by filmListViewModel.state.collectAsState()
    if (loading == null)
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize(),
        ) {
            CircularProgressIndicator()
        }
    else {
        popularPageInfo(filmListViewModel)
    }
}

@Composable
fun FilmTile(film: Film) {
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
                        onClick = {},
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
                        )
                        Text(
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.padding(top = 16.dp),
                            text = film.genres.joinToString(", "){it.genre} + " (${film.year})",
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

@Composable
fun popularPageInfo(filmListViewModel: FilmListViewModel) {
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
            FilmTile(film = it)
        }

    }
}