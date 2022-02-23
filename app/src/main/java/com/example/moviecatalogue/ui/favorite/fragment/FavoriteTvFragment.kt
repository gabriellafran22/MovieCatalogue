package com.example.moviecatalogue.ui.favorite.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.source.local.entity.TvEntity
import com.example.moviecatalogue.databinding.FragmentFavoriteTvBinding
import com.example.moviecatalogue.ui.tv.TvAdapter
import com.example.moviecatalogue.viewmodel.ViewModelFactory

class FavoriteTvFragment : Fragment() {

    private var _fragmentFavoriteTvBinding: FragmentFavoriteTvBinding? = null
    private val fragmentFavoriteTvBinding get() = _fragmentFavoriteTvBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _fragmentFavoriteTvBinding = FragmentFavoriteTvBinding.inflate(layoutInflater, container, false)
        return fragmentFavoriteTvBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val favoriteViewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]

            fragmentFavoriteTvBinding.progressBar.visibility = View.VISIBLE
            favoriteViewModel.getFavoriteTvs().observe(viewLifecycleOwner) {
                fragmentFavoriteTvBinding.progressBar.visibility = View.GONE
                if (it.isNotEmpty()){
                    fragmentFavoriteTvBinding.textNoTvs.visibility = View.GONE
                    showAllFavoriteTvs(it)
                } else {
                    showAllFavoriteTvs(it)
                    fragmentFavoriteTvBinding.textNoTvs.visibility = View.VISIBLE
                    fragmentFavoriteTvBinding.textNoTvs.text = resources.getString(R.string.no_favorite, "Tv Shows")
                }
            }

        }
    }

    private fun showAllFavoriteTvs(movieResults: PagedList<TvEntity>) {
        val movieAdapter = TvAdapter()
        movieAdapter.submitList(movieResults)
        with(fragmentFavoriteTvBinding.rvTvFavorite) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = movieAdapter
        }

    }

}