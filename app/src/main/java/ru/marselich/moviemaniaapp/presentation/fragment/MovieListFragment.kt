package ru.marselich.moviemaniaapp.presentation.fragment

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.marselich.moviemaniaapp.R
import ru.marselich.moviemaniaapp.app.MovieApp
import ru.marselich.moviemaniaapp.databinding.FragmentMovieListBinding
import ru.marselich.moviemaniaapp.presentation.adapter.MovieListAdapter
import ru.marselich.moviemaniaapp.presentation.viewmodel.MovieViewModel
import ru.marselich.moviemaniaapp.presentation.viewmodel.MovieViewModelFactory
import javax.inject.Inject


class MovieListFragment : Fragment() {

    private var _binding: FragmentMovieListBinding? = null
    private val binding: FragmentMovieListBinding
        get() = _binding!!

    @Inject
    lateinit var viewModelFactory: MovieViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(requireActivity(), viewModelFactory)[MovieViewModel::class.java]
    }

    private val component by lazy {
        (requireActivity().application as MovieApp).component
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieListBinding.inflate(inflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        initObserves()
    }


    private fun initRecyclerView() {

        val adapter = MovieListAdapter()

        viewModel.popularMovies.observe(viewLifecycleOwner) {
            adapter.submitList(it.movies)
        }


        binding.swipeRefreshLayout.apply {
            setOnRefreshListener {

                val job = lifecycleScope.async {
                    adapter.currentList.removeAll { true }
                    viewModel.getPopularMovies()
                }

                lifecycleScope.launch {
                    job.await()
                    isRefreshing = false
                }
            }
        }




        binding.rvMovie.adapter = adapter


    }

    private fun initObserves() {
        viewModel.loading.observe(viewLifecycleOwner) {
            if(it) {
                binding.progressBarMovieList.visibility = View.VISIBLE
            } else {
                binding.progressBarMovieList.visibility = View.GONE
            }
        }

        viewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(requireActivity(), "Произошла ошибка $it", Toast.LENGTH_SHORT).show()
            Log.d("NETWORK_ERROR", it)
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            MovieListFragment()
    }
}