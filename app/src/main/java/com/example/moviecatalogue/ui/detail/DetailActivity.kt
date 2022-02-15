package com.example.moviecatalogue.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.MovieEntity
import com.example.moviecatalogue.data.TvEntity
import com.example.moviecatalogue.data.source.remote.response.movie.MovieDetailResponse
import com.example.moviecatalogue.databinding.ActivityDetailBinding
import com.example.moviecatalogue.ui.movies.MovieViewModel
import com.example.moviecatalogue.utils.Constant

class DetailActivity : AppCompatActivity() {

    private lateinit var activityDetailBinding: ActivityDetailBinding
//    private val detailViewModel by viewModels<DetailViewModel>()
    private val constant = Constant()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityDetailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(activityDetailBinding.root)


        val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[DetailViewModel::class.java]

        val type = intent.getStringExtra(EXTRA_TYPE)
        val id = intent.getIntExtra(EXTRA_ID, 0)

        viewModel.isLoading.observe(this) {
            showLoading(it)
        }

        if(type == TYPE_MOVIE){
            supportActionBar?.title = getString(R.string.menu_movies)
            viewModel.getMovieDetailDataFromAPI(id)
            viewModel.movieDetail.observe(this) {
                fillDetailMovie(it)
            }
        } else {
            supportActionBar?.title = getString(R.string.menu_tv_shows)
//            viewModel.setSelectedTv(id)
//            fillDetailTv(viewModel.getTv())
        }

    }

    private fun fillDetailMovie(movie: MovieDetailResponse?) {
        var genre = ""
        var counter = 0
        movie?.genres?.forEach {
            genre += it?.name.toString()
            counter++
            if (counter != movie.genres.size){
                genre += ", "
            }
        }
        with(activityDetailBinding) {
            textTitleDetail.text = movie?.originalTitle
            textDateDetail.text = movie?.releaseDate
            textOverviewDetail.text = movie?.overview
            Glide.with(this@DetailActivity)
                .load(constant.imageUrl + movie?.posterPath)
                .into(imgPosterDetail)
            textGenreDetail.text = genre
            textRuntimeDetail.text = resources.getString(R.string.runtime, movie?.runtime)
        }
    }


    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            activityDetailBinding.progressBar.visibility = View.VISIBLE
        } else {
            activityDetailBinding.progressBar.visibility = View.GONE
        }
    }

    private fun fillDetailTv(tv: TvEntity?) {
        with(activityDetailBinding) {
            textTitleDetail.text = tv?.tvTitle
            textDateDetail.text = tv?.tvReleaseDate
            textOverviewDetail.text = tv?.tvOverview
            Glide.with(this@DetailActivity)
                .load(tv?.tvPoster)
                .into(imgPosterDetail)
        }
    }


    companion object {
        const val EXTRA_ID = "extra_id"
        const val EXTRA_TYPE = "extra_type"
        const val TYPE_MOVIE = "extra_movie"
        const val TYPE_TV = "extra_tv"
    }
}