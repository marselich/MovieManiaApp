package ru.marselich.data.mapper

import ru.marselich.data.BuildConfig
import ru.marselich.data.network.model.MovieDto
import ru.marselich.data.network.model.MovieListDto
import ru.marselich.domain.model.Movie
import ru.marselich.domain.model.MovieList

class MovieMapper {

    fun mapMovieListDtoToEntity(dto: MovieListDto): MovieList =
        MovieList(
            dto.movies.map {
                mapMovieDtoToEntity(it)
            }
        )


    fun mapMovieDtoToEntity(dto: MovieDto): Movie =
        Movie(
            dto.id,
            dto.overview,
            BuildConfig.API_IMAGE_URL + dto.posterPath,
            dto.title,
            dto.rating
        )

}