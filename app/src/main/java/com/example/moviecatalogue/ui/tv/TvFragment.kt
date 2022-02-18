package com.example.moviecatalogue.ui.tv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatalogue.data.source.remote.response.tv.TvResultsItem
import com.example.moviecatalogue.databinding.FragmentMovieBinding
import com.example.moviecatalogue.databinding.FragmentTvBinding
import com.example.moviecatalogue.viewmodel.ViewModelFactory

class TvFragment : Fragment() {

    private var _fragmentTvBinding: FragmentTvBinding? = null
    private val fragmentTvBinding get() = _fragmentTvBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _fragmentTvBinding = FragmentTvBinding.inflate(layoutInflater, container, false)
        return fragmentTvBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance()
            val tvViewModel = ViewModelProvider(this, factory)[TvViewModel::class.java]
            fragmentTvBinding.progressBar.visibility = View.VISIBLE
            tvViewModel.getAllTvs().observe(viewLifecycleOwner) {
                fragmentTvBinding.progressBar.visibility = View.GONE
                showAllTvs(it.results)
            }
        }
    }

    private fun showAllTvs(tvResults: List<TvResultsItem?>?) {
        val tvResultsFiltered: MutableList<TvResultsItem?> = mutableListOf()

        tvResults?.forEach {
            if(!it?.posterPath.isNullOrBlank() && !it?.overview.isNullOrEmpty()){
                tvResultsFiltered.add(it)
            }
        }

        val tvAdapter = TvAdapter()
        tvAdapter.setTvs(tvResultsFiltered)
        with(fragmentTvBinding.rvTvShows) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = tvAdapter
        }

    }
}