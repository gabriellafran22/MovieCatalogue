package com.example.moviecatalogue.ui.tv

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviecatalogue.data.TvEntity
import com.example.moviecatalogue.databinding.ItemsMovieAndTvBinding
import com.example.moviecatalogue.ui.detail.DetailActivity
import java.util.ArrayList

class TvAdapter : RecyclerView.Adapter<TvAdapter.TvViewHolder>() {

    private var listTvs = ArrayList<TvEntity>()

    fun setTvs(tvs: List<TvEntity>?) {
        if (tvs == null) return
        this.listTvs.clear()
        this.listTvs.addAll(tvs)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvViewHolder {
        val itemsTvsBinding =
            ItemsMovieAndTvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvViewHolder(itemsTvsBinding)
    }

    override fun onBindViewHolder(holder: TvAdapter.TvViewHolder, position: Int) {
        val tvs = listTvs[position]
        holder.bind(tvs)
    }

    override fun getItemCount(): Int = listTvs.size

    class TvViewHolder(private val binding: ItemsMovieAndTvBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tv: TvEntity) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(tv.tvPoster)
                    .into(imgPoster)
                textItemTitle.text = tv.tvTitle
                textItemOverview.text = tv.tvOverview
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_ID, tv.tvId)
                    intent.putExtra(DetailActivity.EXTRA_TYPE, DetailActivity.TYPE_TV)
                    it.context.startActivity(intent)
                }
            }
        }
    }


}