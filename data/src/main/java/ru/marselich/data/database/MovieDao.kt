package ru.marselich.data.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.marselich.data.database.model.MovieDbModel

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovies(movies: List<MovieDbModel>)

    @Query("SELECT * FROM movies")
    fun getAllMovies(): PagingSource<Int, MovieDbModel>

    @Query("SELECT * FROM movies WHERE id == :movieId")
    fun getMovie(movieId: Int): Flow<MovieDbModel>

    @Query("DELETE FROM movies")
    suspend fun deleteAllMovies()

}