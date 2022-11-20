package ru.marselich.moviemaniaapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.marselich.domain.model.MovieList
import ru.marselich.domain.useCase.GetPopularMoviesUseCase
import ru.marselich.domain.util.Reaction
import javax.inject.Inject

class MovieViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase
): ViewModel() {

    private val _popularMovies = MutableLiveData<MovieList>()
    val popularMovies: LiveData<MovieList>
        get() = _popularMovies

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    init {
        getPopularMovies()
    }

    fun getPopularMovies() {
        _loading.value = true
        viewModelScope.launch {
            when(getPopularMoviesUseCase()) {
                is Reaction.Success -> {
                    _popularMovies.value = getPopularMoviesUseCase().data!!
                }
                is Reaction.Error -> {
                    _error.value = getPopularMoviesUseCase().message!!
                }
                else -> {}
            }

            _loading.value = false
        }
    }

}