package com.wahidhidayat.themoviedb.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.wahidhidayat.themoviedb.BuildConfig
import com.wahidhidayat.themoviedb.R
import com.wahidhidayat.themoviedb.adapter.ViewPagerAdapter
import com.wahidhidayat.themoviedb.model.Movie
import com.wahidhidayat.themoviedb.model.MovieResult
import com.wahidhidayat.themoviedb.network.APIEndpoints
import com.wahidhidayat.themoviedb.network.APIService
import kotlinx.android.synthetic.main.activity_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        setSupportActionBar(toolbar_detail)
        supportActionBar?.title = "Movie"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar_detail.setNavigationOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        val viewPagerAdapter = ViewPagerAdapter(this, supportFragmentManager)
        view_pager.adapter = viewPagerAdapter
        tab_layout.setupWithViewPager(view_pager)

        pb_detail.visibility = View.VISIBLE

        val movie = intent.getParcelableExtra(EXTRA_MOVIE) as MovieResult
        val movieId = movie.id

        if (movieId != null) {
            fetchDetail(movieId, "en-US")
            Log.i("MOVIE_ID", movieId.toString())
        }
    }

    private fun fetchDetail(movieId: Int, language: String) {
        val service = APIService.buildService(APIEndpoints::class.java)
        val call = service.getMovie(movieId, BuildConfig.API_KEY, language)
        call.enqueue(object : Callback<Movie> {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if (response.isSuccessful) {
                    pb_detail.visibility = View.GONE
                    tv_title_detail.text = response.body()?.title.toString()
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

    fun getMovie(): MovieResult {
        val movie = intent.getParcelableExtra(EXTRA_MOVIE) as MovieResult
        return movie
    }
}