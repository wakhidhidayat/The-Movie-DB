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
import com.wahidhidayat.themoviedb.activity.DetailActivity
import com.wahidhidayat.themoviedb.model.MovieResult
import kotlinx.android.synthetic.main.item_search.view.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class SearchAdapter(
    private val listMovie: ArrayList<MovieResult>,
    private val context: Context
) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: MovieResult) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load(BuildConfig.BASE_IMAGE_URL + "w500" + movie.poster_path)
                    .into(iv_search)
                tv_title_search.text = movie.title
                tv_release_search.text = dateFormat(movie.release_date.toString())
                tv_ratting_search.text = movie.vote_average.toString()
                tv_overview_search.text = movie.overview
            }
        }

        private fun dateFormat(releaseDate: String): String {
            val formatYear = SimpleDateFormat("yyyy", Locale.ENGLISH)
            var year: String? = null
            try {
                val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                val date: Date = simpleDateFormat.parse(releaseDate)
                year = formatYear.format(date).toString()

            } catch (e: ParseException) {
                e.printStackTrace()
            }
            return year.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_search, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listMovie.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie: MovieResult = listMovie[position]
        holder.bind(movie)
        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_MOVIE, movie)
            context.startActivity(intent)
        }
    }
}