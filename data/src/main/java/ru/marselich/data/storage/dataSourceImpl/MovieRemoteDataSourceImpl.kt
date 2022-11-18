package ru.marselich.data.storage.dataSourceImpl

import retrofit2.Response
import ru.marselich.data.network.MovieApiService
import ru.marselich.data.network.model.MovieListDto
import ru.marselich.data.storage.dataSource.MovieRemoteDataSource

class MovieRemoteDataSourceImpl(
    private val api: MovieApiService
) : MovieRemoteDataSource {
    override suspend fun getPopularMovies(): Response<MovieListDto> =
        api.getPopularMovies(BuildConfig.API_KEY)
}