package ru.marselich.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "movies")
data class MovieDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val overview: String?,
    val posterPath: String?,
    val title: String?,
    val rating: String?,
    val releaseDate: String?,
): Serializable