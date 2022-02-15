package com.example.moviecatalogue.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.MovieEntity
import com.example.moviecatalogue.data.TvEntity
import com.example.moviecatalogue.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[DetailViewModel::class.java]

        val type = intent.getStringExtra(EXTRA_TYPE)
        var id = intent.getStringExtra(EXTRA_ID)

        if (id.isNullOrEmpty()) {
            id = "0"
        }

        if(type == TYPE_MOVIE){
            supportActionBar?.title = getString(R.string.menu_movies)
            viewModel.setSelectedMovie(id)
            fillDetailMovie(viewModel.getMovie())
        } else {
            supportActionBar?.title = getString(R.string.menu_tv_shows)
            viewModel.setSelectedTv(id)
            fillDetailTv(viewModel.getTv())
        }

    }

    private fun fillDetailMovie(movie: MovieEntity?) {
        with(binding) {
            textTitleDetail.text = movie?.movieTitle
            textDateDetail.text = movie?.movieReleaseDate
            textOverviewDetail.text = movie?.movieOverview
            Glide.with(this@DetailActivity)
                .load(movie?.moviePoster)
                .into(imgPosterDetail)
        }
    }

    private fun fillDetailTv(tv: TvEntity?) {
        with(binding) {
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