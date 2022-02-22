package com.example.moviecatalogue.ui.movies

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviecatalogue.data.source.local.entity.MovieEntity
import com.example.moviecatalogue.databinding.ItemsMovieAndTvBinding
import com.example.moviecatalogue.ui.detail.DetailActivity
import com.example.moviecatalogue.utils.Constant
import java.util.ArrayList

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var listMovies = ArrayList<MovieEntity?>()

    fun setMovies(movies: List<MovieEntity?>?) {
        if (movies == null) return
        this.listMovies.clear()
        this.listMovies.addAll(movies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemsMoviesBinding =
            ItemsMovieAndTvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemsMoviesBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = listMovies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = listMovies.size

    class MovieViewHolder(private val binding: ItemsMovieAndTvBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val constant = Constant()
        fun bind(movie: MovieEntity?) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(constant.imageUrl + movie?.posterPath)
                    .into(imgPoster)
                textItemTitle.text = movie?.originalTitle
                textItemOverview.text = movie?.overview
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_ID, movie?.id)
                    intent.putExtra(DetailActivity.EXTRA_TYPE, DetailActivity.TYPE_MOVIE)
                    it.context.startActivity(intent)
                }
            }
        }
    }

}