package com.example.moviecatalogue.ui.tv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatalogue.data.source.remote.response.tv.TvResultsItem
import com.example.moviecatalogue.databinding.FragmentTvBinding

class TvFragment : Fragment() {

    private lateinit var fragmentTvBinding: FragmentTvBinding
    private val tvViewModel by viewModels<TvViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentTvBinding = FragmentTvBinding.inflate(layoutInflater, container, false)
        return fragmentTvBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            tvViewModel.tvs.observe(viewLifecycleOwner) {
                showAllTvs(it.results)
            }

            tvViewModel.isLoading.observe(viewLifecycleOwner) {
                showLoading(it)
            }
//            val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[TvViewModel::class.java]
//            val tvShows = viewModel.getTvShows()
//
//            val tvAdapter = TvAdapter()
//            tvAdapter.setTvs(tvShows)
//
//            with(fragmentTvBinding.rvTvShows) {
//                layoutManager = LinearLayoutManager(context)
//                setHasFixedSize(true)
//                adapter = tvAdapter
//            }
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

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            fragmentTvBinding.progressBar.visibility = View.VISIBLE
        } else {
            fragmentTvBinding.progressBar.visibility = View.GONE
        }
    }
}