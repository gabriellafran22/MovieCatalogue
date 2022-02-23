package com.example.moviecatalogue.ui.tv

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviecatalogue.data.source.local.entity.TvEntity
import com.example.moviecatalogue.databinding.ItemsMovieAndTvBinding
import com.example.moviecatalogue.ui.detail.DetailActivity
import com.example.moviecatalogue.utils.imageUrl
import java.util.ArrayList

class TvAdapter : PagedListAdapter<TvEntity, TvAdapter.TvViewHolder>(DIFF_CALLBACK) {

//    private var listTvs = ArrayList<TvEntity>()
//
//    fun setTvs(tvs: List<TvEntity>) {
//        this.listTvs.clear()
//        this.listTvs.addAll(tvs)
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvViewHolder {
        val itemsTvsBinding =
            ItemsMovieAndTvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvViewHolder(itemsTvsBinding)
    }

    override fun onBindViewHolder(holder: TvViewHolder, position: Int) {
//        val tv = listTvs[position]
        val tv = getItem(position)
        if (tv != null) {
            holder.bind(tv)
        }
    }

//    override fun getItemCount(): Int = listTvs.size

    class TvViewHolder(private val binding: ItemsMovieAndTvBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tv: TvEntity?) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(imageUrl + tv?.posterPath)
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

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvEntity>() {
            override fun areItemsTheSame(oldItem: TvEntity, newItem: TvEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TvEntity, newItem: TvEntity): Boolean {
                return oldItem.equals(newItem)
            }
        }
    }

}