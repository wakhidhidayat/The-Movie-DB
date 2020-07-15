package com.wahidhidayat.themoviedb.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wahidhidayat.themoviedb.BuildConfig
import com.wahidhidayat.themoviedb.R
import com.wahidhidayat.themoviedb.activity.DetailActivity
import com.wahidhidayat.themoviedb.adapter.VideoAdapter
import com.wahidhidayat.themoviedb.model.VideoResult
import com.wahidhidayat.themoviedb.model.Videos
import com.wahidhidayat.themoviedb.network.APIEndpoints
import com.wahidhidayat.themoviedb.network.APIService
import kotlinx.android.synthetic.main.fragment_video.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VideoFragment : Fragment() {

    private lateinit var rv: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_video, container, false)

        val detailActivity: DetailActivity = activity as DetailActivity
        val movie = detailActivity.getMovie()
        val movieId = movie.id

        rv = view.rv_videos
        rv.setHasFixedSize(true)
        rv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        if (movieId != null) {
            fetchVideos(movieId, "en-US")
        }

        return view
    }

    private fun fetchVideos(movieId: Int, language: String) {
        val service = APIService.buildService(APIEndpoints::class.java)
        val call = service.getVideos(movieId, BuildConfig.API_KEY, language)
        call.enqueue(object : Callback<Videos> {
            override fun onResponse(call: Call<Videos>, response: Response<Videos>) {
                rv.adapter =
                    VideoAdapter(response.body()?.results as ArrayList<VideoResult>, context)
            }

            override fun onFailure(call: Call<Videos>, t: Throwable?) {
                Toast.makeText(
                    context,
                    t?.localizedMessage.toString(),
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}