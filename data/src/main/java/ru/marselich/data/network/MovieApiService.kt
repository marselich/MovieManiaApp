package ru.marselich.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import ru.marselich.data.network.model.MovieListDto

interface MovieApiService {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String
    ): Response<MovieListDto>

}