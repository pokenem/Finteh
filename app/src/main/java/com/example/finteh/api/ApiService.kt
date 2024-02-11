import kotlinx.serialization.Serializable
import org.json.JSONArray
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

import retrofit2.http.Path

interface ApiService {
    @GET("/api/v2.2/films/top")
    @Headers("X-API-KEY: d07950dc-c214-4cc4-84e0-bd0a69e365f7")
    suspend fun getTop100(): Response<FilmsTop>
    @GET("/api/v2.2/films/{id}")
    @Headers("X-API-KEY: d07950dc-c214-4cc4-84e0-bd0a69e365f7")
    suspend fun getDescById(@Path("id") getId: Int): Response<FilmDesc>
}

@Serializable
data class FilmsTop(
    val films: List<Film>
)

@Serializable
data class Film(
    val filmId: Int,
    val nameRu: String,
    val year: String,
    val posterUrlPreview: String,
    val genres: List<Genre>,
)
@Serializable
data class FilmDesc(
    val kinopoiskId: Int,
    val nameRu: String,
    val year: String,
    val posterUrl: String,
    val genres: List<Genre>,
    val description: String,
)
@Serializable
data class Genre(
    val genre: String,
)
