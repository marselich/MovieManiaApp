package ru.marselich.moviemaniaapp.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.annotation.ExperimentalCoilApi
import coil.load
import ru.marselich.domain.model.Movie
import ru.marselich.moviemaniaapp.databinding.RvItemMovieBinding
import ru.marselich.moviemaniaapp.presentation.util.MovieListDiffUtil
import coil.load
import kotlinx.coroutines.*
import ru.marselich.moviemaniaapp.R

class MovieListAdapter: ListAdapter<Movie, MovieListAdapter.MovieListViewHolder>(MovieListDiffUtil()) {

    class MovieListViewHolder(
        val binding: RvItemMovieBinding
    ): RecyclerView.ViewHolder(binding.root)

    private val scope = CoroutineScope(Dispatchers.Main)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        val binding = RvItemMovieBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MovieListViewHolder(binding)
    }

    @OptIn(ExperimentalCoilApi::class)
    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        val movie = currentList[position]

        with(holder.binding) {
            titleMovie.text = movie.title
            overviewMovie.text = movie.overview
            ratingMovie.text = movie.rating


            scope.launch {

                imageMovie.load(movie.posterPath).apply {

                    await()
                    progressBarImageMovie.visibility = View.GONE

                }
            }

        }
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        scope.cancel()
    }
}