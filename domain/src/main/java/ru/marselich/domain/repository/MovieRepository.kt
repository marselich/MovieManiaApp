package ru.marselich.domain.repository

import ru.marselich.domain.model.MovieList
import ru.marselich.domain.util.Reaction


interface MovieRepository {

    suspend fun getPopularMovies(): Reaction<MovieList>
}