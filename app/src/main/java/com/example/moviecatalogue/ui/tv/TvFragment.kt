package com.example.moviecatalogue.ui.tv

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatalogue.data.source.local.entity.TvEntity
import com.example.moviecatalogue.databinding.FragmentTvBinding
import com.example.moviecatalogue.viewmodel.ViewModelFactory
import com.example.moviecatalogue.vo.Status
import kotlinx.android.synthetic.main.activity_favorite.*

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
            val factory = ViewModelFactory.getInstance(requireActivity())
            val tvViewModel = ViewModelProvider(this, factory)[TvViewModel::class.java]

            tvViewModel.getAllTvs().observe(viewLifecycleOwner) {
                if (it != null) {
                    when (it.status) {
                        Status.LOADING -> fragmentTvBinding.progressBar.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            fragmentTvBinding.progressBar.visibility = View.GONE
                            showAllTvs(it.data!!)
                        }
                        Status.ERROR -> {
                            fragmentTvBinding.progressBar.visibility = View.GONE
                            Toast.makeText(context, "Something Went Wrong", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            }
        }
    }

    private fun showAllTvs(tvResults: PagedList<TvEntity>) {
//        var tvResultsFiltered: PagedList<TvEntity> =
//
//        tvResults.forEach {
//            if(it.posterPath.isNotEmpty() && it.overview.isNotEmpty()){
//                tvResultsFiltered.add(it)
//            }
//        }
        val tvAdapter = TvAdapter()
        tvAdapter.submitList(tvResults)
        with(fragmentTvBinding.rvTvShows) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = tvAdapter
        }

    }
}