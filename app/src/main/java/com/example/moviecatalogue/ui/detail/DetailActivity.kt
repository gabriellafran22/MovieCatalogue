package com.example.moviecatalogue.ui.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.source.local.entity.MovieEntity
import com.example.moviecatalogue.data.source.local.entity.TvEntity
import com.example.moviecatalogue.databinding.ActivityDetailBinding
import com.example.moviecatalogue.utils.Constant
import com.example.moviecatalogue.utils.movieGenreToString
import com.example.moviecatalogue.utils.tvGenreToString
import com.example.moviecatalogue.utils.tvRuntimeToString
import com.example.moviecatalogue.viewmodel.ViewModelFactory
import com.example.moviecatalogue.vo.Status

class DetailActivity : AppCompatActivity() {

    private lateinit var activityDetailBinding: ActivityDetailBinding
    private val constant = Constant()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityDetailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(activityDetailBinding.root)

        val factory = ViewModelFactory.getInstance(this)
        val detailViewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        val type = intent.getStringExtra(EXTRA_TYPE)
        val id = intent.getIntExtra(EXTRA_ID, 0)

        activityDetailBinding.progressBar.visibility = View.VISIBLE
        if (type == TYPE_MOVIE) {
            supportActionBar?.title = getString(R.string.menu_movies)
            detailViewModel.getMovieDetailData(id).observe(this) {
                if (it != null) {
                    when(it.status){
                        Status.LOADING -> activityDetailBinding.progressBar.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            activityDetailBinding.progressBar.visibility = View.GONE
                            fillDetailMovie(it.data!!)
                        }
                        Status.ERROR -> {
                            activityDetailBinding.progressBar.visibility = View.GONE
                            Toast.makeText(this, "Something Went Wrong", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        } else {
            supportActionBar?.title = getString(R.string.menu_tv_shows)
            detailViewModel.getTvDetailData(id).observe(this) {
                if (it != null) {
                    when(it.status){
                        Status.LOADING -> activityDetailBinding.progressBar.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            activityDetailBinding.progressBar.visibility = View.GONE
                            fillDetailTv(it.data!!)
                        }
                        Status.ERROR -> {
                            activityDetailBinding.progressBar.visibility = View.GONE
                            Toast.makeText(this, "Something Went Wrong", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }

    }

    private fun fillDetailMovie(movie: MovieEntity) {
        with(activityDetailBinding) {
            textTitleDetail.text = movie.originalTitle
            textDateDetail.text = movie.releaseDate
            textOverviewDetail.text = movie.overview
            Glide.with(this@DetailActivity)
                .load(constant.imageUrl + movie.posterPath)
                .into(imgPosterDetail)
            textGenreDetail.text = movie.genres
            textRuntimeDetail.text = resources.getString(R.string.runtime, movie.runtime)
        }
    }

    private fun fillDetailTv(tv: TvEntity) {

        with(activityDetailBinding) {
            textTitleDetail.text = tv.name
            textDateDetail.text = tv.firstAirDate
            textOverviewDetail.text = tv.overview
            Glide.with(this@DetailActivity)
                .load(constant.imageUrl + tv.posterPath)
                .into(imgPosterDetail)
            textGenreDetail.text = tv.genres
            textRuntimeDetail.text = resources.getString(R.string.runtime, tv.episodeRunTime)
        }
    }

    companion object {
        const val EXTRA_ID = "extra_id"
        const val EXTRA_TYPE = "extra_type"
        const val TYPE_MOVIE = "extra_movie"
        const val TYPE_TV = "extra_tv"
    }
}
