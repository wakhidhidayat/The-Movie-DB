package com.wahidhidayat.themoviedb.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wahidhidayat.themoviedb.BuildConfig
import com.wahidhidayat.themoviedb.R
import com.wahidhidayat.themoviedb.model.MovieResult
import com.wahidhidayat.themoviedb.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.item_now_playing.view.*

class NowPlayingAdapter(
    private val listMovie: List<MovieResult>,
    private val context: Context?
) : RecyclerView.Adapter<NowPlayingAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: MovieResult) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load(BuildConfig.BASE_IMAGE_URL + "w500" + movie.poster_path)
                    .into(iv_now_playing)
                itemView.setOnClickListener {
                    val intent = Intent(context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_MOVIE, movie)
                    context?.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_now_playing, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listMovie.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie: MovieResult = listMovie[position]
        holder.bind(movie)
    }

}