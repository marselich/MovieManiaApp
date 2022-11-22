package ru.marselich.domain.model

data class Movie(
    val id: Int,
    val overview: String?,
    val posterPath: String?,
    val title: String?,
    val rating: String?,
    val releaseDate: String?,
)
