package com.example.moviecatalogue.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.MovieEntity
import com.example.moviecatalogue.data.TvEntity
import com.example.moviecatalogue.data.source.remote.response.movie.MovieDetailResponse
import com.example.moviecatalogue.data.source.remote.response.tv.TvDetailResponse
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


        val viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[DetailViewModel::class.java]

        val type = intent.getStringExtra(EXTRA_TYPE)
        val id = intent.getIntExtra(EXTRA_ID, 0)

        viewModel.isLoading.observe(this) {
            showLoading(it)
        }

        if (type == TYPE_MOVIE) {
            supportActionBar?.title = getString(R.string.menu_movies)
            viewModel.getMovieDetailDataFromAPI(id)
            viewModel.movieDetail.observe(this) {
                fillDetailMovie(it)
            }
        } else {
            supportActionBar?.title = getString(R.string.menu_tv_shows)
            viewModel.getTvDetailDataFromAPI(id)
            viewModel.tvDetail.observe(this) {
                fillDetailTv(it)
            }
        }

    }

    private fun fillDetailMovie(movie: MovieDetailResponse?) {
        var genre = ""
        var counter = 0
        movie?.genres?.forEach {
            genre += it?.name.toString()
            counter++
            if (counter != movie.genres.size) {
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

    private fun fillDetailTv(tv: TvDetailResponse?) {
        var genre = ""
        var counter = 0
        tv?.genres?.forEach {
            genre += it?.name.toString()
            counter++
            if (counter != tv.genres.size) {
                genre += ", "
            }
        }

        var runtime = ""
        val episodeRuntime = tv?.episodeRunTime
        if (episodeRuntime?.size == 1) {
            runtime = resources.getString(R.string.runtime, episodeRuntime[0])
        }  else {
            counter = 0
            episodeRuntime?.forEach {
                counter++
                runtime += resources.getString(R.string.runtime, it)
                if (counter != episodeRuntime.size) {
                    runtime += ", "
                }
            }
        }

        with(activityDetailBinding) {
            textTitleDetail.text = tv?.name
            textDateDetail.text = tv?.firstAirDate
            textOverviewDetail.text = tv?.overview
            Glide.with(this@DetailActivity)
                .load(constant.imageUrl + tv?.posterPath)
                .into(imgPosterDetail)
            textGenreDetail.text = genre
            textRuntimeDetail.text = runtime
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            activityDetailBinding.progressBar.visibility = View.VISIBLE
        } else {
            activityDetailBinding.progressBar.visibility = View.GONE
        }
    }


//    private fun fillDetailTv(tv: TvEntity?) {
//        with(activityDetailBinding) {
//            textTitleDetail.text = tv?.tvTitle
//            textDateDetail.text = tv?.tvReleaseDate
//            textOverviewDetail.text = tv?.tvOverview
//            Glide.with(this@DetailActivity)
//                .load(tv?.tvPoster)
//                .into(imgPosterDetail)
//        }
//    }


    companion object {
        const val EXTRA_ID = "extra_id"
        const val EXTRA_TYPE = "extra_type"
        const val TYPE_MOVIE = "extra_movie"
        const val TYPE_TV = "extra_tv"
    }
}
