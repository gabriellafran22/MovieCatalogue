package com.example.moviecatalogue.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatalogue.data.source.remote.response.movie.MovieResultsItem
import com.example.moviecatalogue.databinding.FragmentMovieBinding
import com.example.moviecatalogue.viewmodel.ViewModelFactory

class MovieFragment : Fragment() {

    private lateinit var fragmentMovieBinding: FragmentMovieBinding

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
            val factory = ViewModelFactory.getInstance()
            val movieViewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]

            fragmentMovieBinding.progressBar.visibility = View.VISIBLE

            movieViewModel.getAllMovies().observe(viewLifecycleOwner) {
                fragmentMovieBinding.progressBar.visibility = View.GONE
                showAllMovies(it.results)
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

}