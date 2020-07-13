package com.wahidhidayat.themoviedb.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.wahidhidayat.themoviedb.BuildConfig
import com.wahidhidayat.themoviedb.R
import com.wahidhidayat.themoviedb.adapter.CastAdapter
import com.wahidhidayat.themoviedb.model.Cast
import com.wahidhidayat.themoviedb.model.Credits
import com.wahidhidayat.themoviedb.model.Movie
import com.wahidhidayat.themoviedb.model.Result
import com.wahidhidayat.themoviedb.network.APIEndpoints
import com.wahidhidayat.themoviedb.network.APIService
import kotlinx.android.synthetic.main.activity_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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
        val movieId = movie.id

        rv_credits.setHasFixedSize(true)
        rv_credits.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        if (movieId != null) {
            fetchDetail(movieId, "en-US")
            fetchCredits(movieId)
        }
    }

    private fun fetchDetail(movieId: Int, language: String) {
        val service = APIService.buildService(APIEndpoints::class.java)
        val call = service.getMovie(movieId, BuildConfig.API_KEY, language)
        call.enqueue(object : Callback<Movie> {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if (response.isSuccessful) {
                    tv_title_detail.text = response.body()?.title.toString()
                    tv_release.text = dateFormat(response.body()?.release_date.toString())
                    tv_ratting.text = response.body()?.vote_average.toString()
                    tv_overview.text = response.body()?.overview.toString()
                    Glide.with(this@DetailActivity)
                        .load(BuildConfig.BASE_IMAGE_URL + "w500" + response.body()?.backdrop_path)
                        .into(iv_detail)
                }
            }

            override fun onFailure(call: Call<Movie>, t: Throwable?) {
                Toast.makeText(
                    this@DetailActivity,
                    t?.localizedMessage.toString(),
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    private fun fetchCredits(movieId: Int) {
        val service = APIService.buildService(APIEndpoints::class.java)
        val call = service.getCredits(movieId, BuildConfig.API_KEY)
        call.enqueue(object : Callback<Credits> {
            override fun onResponse(call: Call<Credits>, response: Response<Credits>) {
                if (response.isSuccessful) {
                    rv_credits.adapter =
                        CastAdapter(response.body()?.cast as ArrayList<Cast>, this@DetailActivity)
                }
            }

            override fun onFailure(call: Call<Credits>, t: Throwable?) {
                Toast.makeText(
                    this@DetailActivity,
                    t?.localizedMessage.toString(),
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
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