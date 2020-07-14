package com.wahidhidayat.themoviedb.fragment

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wahidhidayat.themoviedb.BuildConfig
import com.wahidhidayat.themoviedb.R
import com.wahidhidayat.themoviedb.activity.DetailActivity
import com.wahidhidayat.themoviedb.adapter.CastAdapter
import com.wahidhidayat.themoviedb.model.Cast
import com.wahidhidayat.themoviedb.model.Credits
import com.wahidhidayat.themoviedb.model.Movie
import com.wahidhidayat.themoviedb.network.APIEndpoints
import com.wahidhidayat.themoviedb.network.APIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class InfoFragment : Fragment() {
    private lateinit var tvOverview: TextView
    private lateinit var tvRelase: TextView
    private lateinit var tvRatting: TextView
    private lateinit var rvCredits: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_info, container, false)

        val detailActivity: DetailActivity = activity as DetailActivity
        val movie = detailActivity.getMovie()
        val movieId = movie.id

        tvOverview = view.findViewById(R.id.tv_overview)
        tvRelase = view.findViewById(R.id.tv_release)
        tvRatting = view.findViewById(R.id.tv_ratting)
        rvCredits = view.findViewById(R.id.rv_credits)

        rvCredits.setHasFixedSize(true)
        rvCredits.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        if (movieId != null) {
            fetchInfo(movieId, "en-US")
            fetchCredits(movieId)
        }

        return view
    }

    private fun fetchInfo(movieId: Int, language: String) {
        val service = APIService.buildService(APIEndpoints::class.java)
        val call = service.getMovie(movieId, BuildConfig.API_KEY, language)
        call.enqueue(object : Callback<Movie> {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if (response.isSuccessful) {
                    tvOverview.text = response.body()?.overview.toString()
                    tvRelase.text = dateFormat(response.body()?.release_date.toString())
                    tvRatting.text = response.body()?.vote_average.toString()
                }
            }

            override fun onFailure(call: Call<Movie>, t: Throwable?) {
                Toast.makeText(
                    context,
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
                    rvCredits.adapter =
                        CastAdapter(response.body()?.cast as ArrayList<Cast>, context)
                }
            }

            override fun onFailure(call: Call<Credits>, t: Throwable?) {
                Toast.makeText(
                    context,
                    t?.localizedMessage.toString(),
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun dateFormat(releaseDate: String): String {
        val formatYear = SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH)
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