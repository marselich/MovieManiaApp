package ru.marselich.domain.useCase

import ru.marselich.domain.model.MovieList
import ru.marselich.domain.repository.MovieRepository
import ru.marselich.domain.util.Reaction

class GetPopularMoviesUseCase(
    private val repository: MovieRepository
) {

    suspend operator fun invoke(): Reaction<MovieList> = repository.getPopularMovies()

}