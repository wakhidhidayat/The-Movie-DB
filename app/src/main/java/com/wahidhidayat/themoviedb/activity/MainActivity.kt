package com.wahidhidayat.themoviedb.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.wahidhidayat.themoviedb.BuildConfig
import com.wahidhidayat.themoviedb.R
import com.wahidhidayat.themoviedb.adapter.MovieAdapter
import com.wahidhidayat.themoviedb.model.Movie
import com.wahidhidayat.themoviedb.model.Result
import com.wahidhidayat.themoviedb.network.APIService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_main.setHasFixedSize(true)
        rv_main.layoutManager = LinearLayoutManager(this)

        fetch(BuildConfig.API_KEY, "en-US")
    }

    private fun fetch(apiKey: String, language: String) {
        val retrofit = Retrofit
            .Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(APIService::class.java)
        val call = service.getMovies(apiKey, language)
        call.enqueue(object : Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if (response.isSuccessful) {
                    rv_main.adapter = MovieAdapter(
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