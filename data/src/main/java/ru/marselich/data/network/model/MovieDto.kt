package ru.marselich.data.network.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MovieDto(
    val id: Int,
    val overview: String?,
    @SerializedName("poster_path")
    val posterPath: String?,
    val title: String?,
    @SerializedName("vote_average")
    val rating: String?
): Serializable
