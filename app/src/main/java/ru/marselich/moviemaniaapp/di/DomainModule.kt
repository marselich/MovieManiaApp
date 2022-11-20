package ru.marselich.moviemaniaapp.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.marselich.domain.repository.MovieRepository
import ru.marselich.domain.useCase.GetPopularMoviesUseCase

@Module
interface DomainModule {

    companion object {
        @Provides
        fun provideGetPopularMoviesUseCase(repository: MovieRepository): GetPopularMoviesUseCase =
            GetPopularMoviesUseCase(repository)

    }
}