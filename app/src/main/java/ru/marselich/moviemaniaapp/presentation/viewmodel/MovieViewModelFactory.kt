package ru.marselich.moviemaniaapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.marselich.domain.useCase.GetPopularMoviesUseCase
import javax.inject.Inject

class MovieViewModelFactory @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass == MovieViewModel::class.java) {
            return MovieViewModel(getPopularMoviesUseCase) as T
        }
        throw Exception("Unknown view model class")
    }
}