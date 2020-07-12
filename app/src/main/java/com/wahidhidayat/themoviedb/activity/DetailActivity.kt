package com.wahidhidayat.themoviedb.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.wahidhidayat.themoviedb.BuildConfig
import com.wahidhidayat.themoviedb.R
import com.wahidhidayat.themoviedb.model.Result
import kotlinx.android.synthetic.main.activity_detail.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        setSupportActionBar(toolbar_detail)
        supportActionBar?.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar_detail.setNavigationOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

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
        tv_release.text = dateFormat(releaseDate)
        tv_overview.text = overview
    }

    @RequiresApi(Build.VERSION_CODES.O)
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