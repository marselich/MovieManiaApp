package ru.marselich.data.storage.dataSource

import retrofit2.Response
import ru.marselich.data.network.model.MovieListDto
import ru.marselich.domain.model.MovieList

interface MovieRemoteDataSource {

    suspend fun getPopularMovies(): Response<MovieListDto>

}