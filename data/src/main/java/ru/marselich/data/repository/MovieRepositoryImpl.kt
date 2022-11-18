package ru.marselich.data.repository

import ru.marselich.data.mapper.MovieMapper
import ru.marselich.data.storage.dataSource.MovieRemoteDataSource
import ru.marselich.domain.model.MovieList
import ru.marselich.domain.repository.MovieRepository
import ru.marselich.domain.util.Reaction

class MovieRepositoryImpl(
    private val remoteDataSource: MovieRemoteDataSource,
    private val mapper: MovieMapper
) : MovieRepository {
    override suspend fun getPopularMovies(): Reaction<MovieList> {
        val response = remoteDataSource.getPopularMovies()
        if(response.isSuccessful) {
            response.body()?.let {
                return Reaction.Success(mapper.mapMovieListDtoToEntity(it))
            }
        }
        return Reaction.Error(response.message())
    }
}