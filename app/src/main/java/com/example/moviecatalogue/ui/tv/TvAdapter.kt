package com.example.moviecatalogue.ui.tv

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviecatalogue.data.source.local.entity.TvEntity
import com.example.moviecatalogue.databinding.ItemsMovieAndTvBinding
import com.example.moviecatalogue.ui.detail.DetailActivity
import com.example.moviecatalogue.utils.Constant
import java.util.ArrayList

class TvAdapter : RecyclerView.Adapter<TvAdapter.TvViewHolder>() {

    private var listTvs = ArrayList<TvEntity>()

    fun setTvs(tvs: List<TvEntity>) {
//        if (tvs == null) return
        this.listTvs.clear()
        this.listTvs.addAll(tvs)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvViewHolder {
        val itemsTvsBinding =
            ItemsMovieAndTvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvViewHolder(itemsTvsBinding)
    }

    override fun onBindViewHolder(holder: TvViewHolder, position: Int) {
        val tv = listTvs[position]
        holder.bind(tv)
    }

    override fun getItemCount(): Int = listTvs.size

    class TvViewHolder(private val binding: ItemsMovieAndTvBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val constant = Constant()
        fun bind(tv: TvEntity?) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(constant.imageUrl + tv?.posterPath)
                    .into(imgPoster)
                textItemTitle.text = tv?.name
                textItemOverview.text = tv?.overview
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_ID, tv?.id)
                    intent.putExtra(DetailActivity.EXTRA_TYPE, DetailActivity.TYPE_TV)
                    it.context.startActivity(intent)
                }
            }
        }
    }
}