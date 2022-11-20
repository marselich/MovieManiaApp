package ru.marselich.moviemaniaapp.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.marselich.data.mapper.MovieMapper
import ru.marselich.data.network.MovieApiFactory
import ru.marselich.data.network.MovieApiService
import ru.marselich.data.repository.MovieRepositoryImpl
import ru.marselich.data.storage.dataSource.MovieRemoteDataSource
import ru.marselich.data.storage.dataSourceImpl.MovieRemoteDataSourceImpl
import ru.marselich.domain.repository.MovieRepository

@Module
interface DataModule {

    @Binds
    fun bindMovieRemoteDataSource(impl: MovieRemoteDataSourceImpl): MovieRemoteDataSource

    @Binds
    fun bindMovieRepository(impl: MovieRepositoryImpl): MovieRepository


    companion object {

        @Provides
        fun provideMovieMapper(): MovieMapper = MovieMapper()

        @Provides
        fun provideMovieApi(): MovieApiService {
            return MovieApiFactory.getInstance()
        }

        @Provides
        fun provideMovieRepositoryImpl(
            remoteDataSource: MovieRemoteDataSource,
            mapper: MovieMapper
        ): MovieRepositoryImpl {
            return MovieRepositoryImpl(remoteDataSource, mapper)
        }

        @Provides
        fun provideMovieRemoteDataSourceImpl(
            api: MovieApiService
        ): MovieRemoteDataSourceImpl {
            return MovieRemoteDataSourceImpl(api)
        }


    }
}