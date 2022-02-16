package com.example.moviecatalogue.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatalogue.data.source.remote.response.movie.MovieResultsItem
import com.example.moviecatalogue.databinding.FragmentMovieBinding

class MovieFragment : Fragment() {

    private lateinit var fragmentMovieBinding: FragmentMovieBinding
    private val movieViewModel by viewModels<MovieViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentMovieBinding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return fragmentMovieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            movieViewModel.movies.observe(viewLifecycleOwner) {
                showAllMovies(it.results)
            }

            movieViewModel.isLoading.observe(viewLifecycleOwner) {
                showLoading(it)
            }

        }
    }

    private fun showAllMovies(movieResults: List<MovieResultsItem?>?) {
        val movieAdapter = MovieAdapter()
        movieAdapter.setMovies(movieResults)
        with(fragmentMovieBinding.rvMovie) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = movieAdapter
        }

    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            fragmentMovieBinding.progressBar.visibility = View.VISIBLE
        } else {
            fragmentMovieBinding.progressBar.visibility = View.GONE
        }
    }
}