package ru.marselich.data.database


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [], version = 1, exportSchema = false)
abstract class MovieDataBase : RoomDatabase() {
    abstract val movieDao: MovieDao

    companion object {

        private var INSTANCE: MovieDataBase? = null
        private const val DATABASE_NAME = "movies.db"

        fun getInstance(context: Context): MovieDataBase {
            INSTANCE?.let {
                return it
            }
            synchronized(Any()) {
                INSTANCE?.let {
                    return it
                }

                val db = Room.databaseBuilder(
                    context,
                    MovieDataBase::class.java,
                    DATABASE_NAME
                ).build()

                INSTANCE = db
                return db
            }
        }

    }
}