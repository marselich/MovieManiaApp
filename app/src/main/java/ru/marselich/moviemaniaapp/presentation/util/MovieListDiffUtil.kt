package ru.marselich.moviemaniaapp.presentation.util

import androidx.recyclerview.widget.DiffUtil
import ru.marselich.domain.model.Movie

class MovieListDiffUtil : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
        oldItem.id == newItem.id


    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
        oldItem == newItem

}