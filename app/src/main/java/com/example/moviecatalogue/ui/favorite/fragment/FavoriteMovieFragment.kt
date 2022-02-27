package com.example.moviecatalogue.ui.favorite.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.source.local.entity.MovieEntity
import com.example.moviecatalogue.databinding.FragmentFavoriteMovieBinding
import com.example.moviecatalogue.ui.movies.MovieAdapter
import com.example.moviecatalogue.viewmodel.ViewModelFactory

class FavoriteMovieFragment : Fragment() {

    private var _fragmentFavoriteMovieBinding: FragmentFavoriteMovieBinding? = null
    private val fragmentFavoriteMovieBinding get() = _fragmentFavoriteMovieBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _fragmentFavoriteMovieBinding = FragmentFavoriteMovieBinding.inflate(layoutInflater, container, false)
        return fragmentFavoriteMovieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val favoriteViewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]

            fragmentFavoriteMovieBinding.progressBar.visibility = View.VISIBLE
            favoriteViewModel.getFavoriteMovies().observe(viewLifecycleOwner) {
                fragmentFavoriteMovieBinding.progressBar.visibility = View.GONE
                if (it.isNotEmpty()){
                    fragmentFavoriteMovieBinding.textNoMovies.visibility = View.GONE
                    showAllFavoriteMovies(it)
                } else {
                    showAllFavoriteMovies(it)
                    fragmentFavoriteMovieBinding.textNoMovies.visibility = View.VISIBLE
                    fragmentFavoriteMovieBinding.textNoMovies.text = resources.getString(R.string.no_favorite, "Movies")
                }
            }

        }
    }

    private fun showAllFavoriteMovies(movieResults: PagedList<MovieEntity>) {
        val movieAdapter = MovieAdapter()
        movieAdapter.submitList(movieResults)
        with(fragmentFavoriteMovieBinding.rvMovieFavorite) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = movieAdapter
        }

    }

}