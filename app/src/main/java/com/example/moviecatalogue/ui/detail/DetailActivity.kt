package com.example.moviecatalogue.ui.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.source.remote.response.movie.GenresItem
import com.example.moviecatalogue.data.source.remote.response.movie.MovieDetailResponse
import com.example.moviecatalogue.data.source.remote.response.tv.TvDetailResponse
import com.example.moviecatalogue.data.source.remote.response.tv.TvGenresItem
import com.example.moviecatalogue.databinding.ActivityDetailBinding
import com.example.moviecatalogue.utils.Constant
import com.example.moviecatalogue.viewmodel.ViewModelFactory

class DetailActivity : AppCompatActivity() {

    private lateinit var activityDetailBinding: ActivityDetailBinding
    private val constant = Constant()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityDetailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(activityDetailBinding.root)

        val factory = ViewModelFactory.getInstance()
        val detailViewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        val type = intent.getStringExtra(EXTRA_TYPE)
        val id = intent.getIntExtra(EXTRA_ID, 0)

        activityDetailBinding.progressBar.visibility = View.VISIBLE
        if (type == TYPE_MOVIE) {
            supportActionBar?.title = getString(R.string.menu_movies)
            detailViewModel.getMovieDetailDataFromAPI(id).observe(this) {
                activityDetailBinding.progressBar.visibility = View.GONE
                fillDetailMovie(it)
            }
        } else {
            supportActionBar?.title = getString(R.string.menu_tv_shows)
            detailViewModel.getTvDetailDataFromAPI(id).observe(this) {
                activityDetailBinding.progressBar.visibility = View.GONE
                fillDetailTv(it)
            }
        }

    }

    private fun fillDetailMovie(movie: MovieDetailResponse?) {
        with(activityDetailBinding) {
            textTitleDetail.text = movie?.originalTitle
            textDateDetail.text = movie?.releaseDate
            textOverviewDetail.text = movie?.overview
            Glide.with(this@DetailActivity)
                .load(constant.imageUrl + movie?.posterPath)
                .into(imgPosterDetail)
            textGenreDetail.text = movieGenreToString(movie?.genres)
            textRuntimeDetail.text = resources.getString(R.string.runtime, movie?.runtime)
        }
    }

    private fun movieGenreToString(genresItem: List<GenresItem?>?): String{
        var genre = ""
        var counter = 0

        genresItem?.forEach {
            genre += it?.name.toString()
            counter++
            if (counter != genresItem.size) {
                genre += ", "
            }
        }
        return genre
    }

    private fun fillDetailTv(tv: TvDetailResponse?) {

        with(activityDetailBinding) {
            textTitleDetail.text = tv?.name
            textDateDetail.text = tv?.firstAirDate
            textOverviewDetail.text = tv?.overview
            Glide.with(this@DetailActivity)
                .load(constant.imageUrl + tv?.posterPath)
                .into(imgPosterDetail)
            textGenreDetail.text = tvGenreToString(tv?.genres)
            textRuntimeDetail.text = tvRuntimeToString(tv?.episodeRunTime)
        }
    }

    private fun tvGenreToString(genresItem: List<TvGenresItem?>?): String{
        var genre = ""
        var counter = 0

        genresItem?.forEach {
            genre += it?.name.toString()
            counter++
            if (counter != genresItem.size) {
                genre += ", "
            }
        }
        return genre
    }

    private fun tvRuntimeToString(episodeRuntime: List<Int?>?): String{
        var counter = 0
        var runtime = ""

        if (episodeRuntime?.size == 1) {
            runtime = resources.getString(R.string.runtime, episodeRuntime[0])
        } else {
            counter = 0
            episodeRuntime?.forEach {
                counter++
                runtime += resources.getString(R.string.runtime, it)
                if (counter != episodeRuntime.size) {
                    runtime += ", "
                }
            }
        }

        return runtime
    }

    companion object {
        const val EXTRA_ID = "extra_id"
        const val EXTRA_TYPE = "extra_type"
        const val TYPE_MOVIE = "extra_movie"
        const val TYPE_TV = "extra_tv"
    }
}
