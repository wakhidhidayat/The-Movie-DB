package com.wahidhidayat.themoviedb.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wahidhidayat.themoviedb.BuildConfig
import com.wahidhidayat.themoviedb.R
import com.wahidhidayat.themoviedb.adapter.NowPlayingAdapter
import com.wahidhidayat.themoviedb.adapter.PopularAdapter
import com.wahidhidayat.themoviedb.adapter.UpcomingAdapter
import com.wahidhidayat.themoviedb.model.Movie
import com.wahidhidayat.themoviedb.model.Result
import com.wahidhidayat.themoviedb.network.APIEndpoints
import com.wahidhidayat.themoviedb.network.APIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieFragment : Fragment() {
    private lateinit var rvNowPlaying: RecyclerView
    private lateinit var rvUpcoming: RecyclerView
    private lateinit var rvPopular: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val ui = inflater.inflate(R.layout.fragment_movie, container, false)

        rvNowPlaying = ui.findViewById(R.id.rv_now_playing)
        rvUpcoming = ui.findViewById(R.id.rv_upcoming)
        rvPopular = ui.findViewById(R.id.rv_popular)

        rvNowPlaying.setHasFixedSize(true)
        rvNowPlaying.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        rvUpcoming.setHasFixedSize(true)
        rvUpcoming.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        rvPopular.setHasFixedSize(true)
        rvPopular.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        fetch(BuildConfig.API_KEY, "en-US")
        fetchUpcoming(BuildConfig.API_KEY, "en-US")
        fetchPopular(BuildConfig.API_KEY, "en-US")

        return ui
    }

    private fun fetch(apiKey: String, language: String) {
        val service = APIService.buildService(APIEndpoints::class.java)
        val call = service.getMovies(apiKey, language)
        call.enqueue(object : Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if (response.isSuccessful) {
                    rvNowPlaying.adapter = activity?.let {
                        NowPlayingAdapter(
                            response.body()!!.result as ArrayList<Result>,
                            it
                        )
                    }
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
                    rvUpcoming.adapter = activity?.let {
                        UpcomingAdapter(
                            response.body()!!.result as ArrayList<Result>,
                            it
                        )
                    }
                }
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun fetchPopular(apiKey: String, language: String) {
        val service = APIService.buildService(APIEndpoints::class.java)
        val call = service.getPopular(apiKey, language)
        call.enqueue(object : Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if (response.isSuccessful) {
                    rvPopular.adapter = activity?.let {
                        PopularAdapter(
                            response.body()!!.result as ArrayList<Result>,
                            it
                        )
                    }
                }
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}