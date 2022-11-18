package ru.marselich.data.mapper

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
            dto.posterPath,
            dto.title,
            dto.rating
        )

}