import kotlinx.serialization.Serializable
import org.json.JSONArray
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

import retrofit2.http.Path

interface ApiService {
    @GET("/api/v2.2/films/top?type=TOP_100_POPULAR_FILMS")
    @Headers("X-API-KEY: e30ffed0-76ab-4dd6-b41f-4c9da2b2735b")
    suspend fun getTop100(): Response<FilmsTop>
    @GET("/api/v2.2/films/{id}")
    @Headers("X-API-KEY: e30ffed0-76ab-4dd6-b41f-4c9da2b2735b")
    suspend fun getDescById(@Path("id") getId: Int): Response<Film>
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
    val posterUrl: String,
    val genres: List<Genre>,
    val favourite: Boolean = false,
    val kinopoiskId: Int,
    val description: String,
    val countries: List<Country>,
)
@Serializable
data class FilmDesc(
    val kinopoiskId: Int,
    val nameRu: String,
    val year: String,
    val posterUrl: String,
    val genres: List<Genre>,
    val description: String,
    val countries: List<Country>,
)

@Serializable
data class Country(
    val country: String,
)
@Serializable
data class Genre(
    val genre: String,
)
