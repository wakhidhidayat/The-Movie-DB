package com.wahidhidayat.themoviedb.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wahidhidayat.themoviedb.BuildConfig
import com.wahidhidayat.themoviedb.R
import com.wahidhidayat.themoviedb.model.Result
import kotlinx.android.synthetic.main.item_now_playing_movie.view.*
import kotlinx.android.synthetic.main.item_upcoming_movie.view.*

class MovieUpcomingAdapter(
    private val listMovie: ArrayList<Result>,
    private val context: Context
) : RecyclerView.Adapter<MovieUpcomingAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(movie: Result) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load(BuildConfig.BASE_IMAGE_URL + "w500" + movie.backdrop_path)
                    .into(iv_poster_upcoming_movie)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_upcoming_movie, parent, false)
        return MovieUpcomingAdapter.ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listMovie.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie: Result = listMovie[position]
        holder.bind(movie)
    }
}