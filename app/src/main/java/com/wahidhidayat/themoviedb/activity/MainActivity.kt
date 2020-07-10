package com.wahidhidayat.themoviedb.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.wahidhidayat.themoviedb.BuildConfig
import com.wahidhidayat.themoviedb.R
import com.wahidhidayat.themoviedb.adapter.MovieAdapter
import com.wahidhidayat.themoviedb.adapter.MovieUpcomingAdapter
import com.wahidhidayat.themoviedb.model.Movie
import com.wahidhidayat.themoviedb.model.Result
import com.wahidhidayat.themoviedb.network.APIEndpoints
import com.wahidhidayat.themoviedb.network.APIService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_now_playing.setHasFixedSize(true)
        rv_now_playing.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        rv_upcoming.setHasFixedSize(true)
        rv_upcoming.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        fetch(BuildConfig.API_KEY, "en-US")
        fetchUpcoming(BuildConfig.API_KEY, "en-US")
    }

    private fun fetch(apiKey: String, language: String) {
        val service = APIService.buildService(APIEndpoints::class.java)
        val call = service.getMovies(apiKey, language)
        call.enqueue(object : Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if (response.isSuccessful) {
                    rv_now_playing.adapter = MovieAdapter(
                        response.body()!!.result as ArrayList<Result>,
                        this@MainActivity
                    )
                }
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun fetchUpcoming(apiKey: String, language: String) {
        val service = APIService.buildService(APIEndpoints::class.java)
        val call = service.getUpcoming(apiKey, language)
        call.enqueue(object : Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if (response.isSuccessful) {
                    rv_upcoming.adapter = MovieUpcomingAdapter(
                        response.body()!!.result as ArrayList<Result>,
                        this@MainActivity
                    )
                }
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}