package ru.marselich.data.network.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MovieListDto(
    @SerializedName("results")
   val movies: List<MovieDto>
): Serializable
