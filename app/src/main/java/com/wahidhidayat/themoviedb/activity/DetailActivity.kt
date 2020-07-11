package com.wahidhidayat.themoviedb.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.wahidhidayat.themoviedb.BuildConfig
import com.wahidhidayat.themoviedb.R
import com.wahidhidayat.themoviedb.model.Movie
import com.wahidhidayat.themoviedb.model.Result
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val movie = intent.getParcelableExtra(EXTRA_MOVIE) as Result
        val title = movie.title.toString()
        val posterPath = movie.poster_path.toString()
        val backdropPath = movie.backdrop_path.toString()
        val overview = movie.overview.toString()
        val releaseDate = movie.release_date.toString()
        val voteAverage = movie.vote_average.toString()

        tv_title_detail.text = title
        Glide.with(this)
            .load(BuildConfig.BASE_IMAGE_URL + "w500" + backdropPath)
            .into(iv_detail)
        tv_overview.text = overview
    }
}