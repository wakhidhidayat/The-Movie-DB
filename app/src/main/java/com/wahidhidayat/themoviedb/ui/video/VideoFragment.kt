package com.wahidhidayat.themoviedb.ui.video

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.wahidhidayat.themoviedb.R
import com.wahidhidayat.themoviedb.adapter.VideoAdapter
import com.wahidhidayat.themoviedb.model.VideoResult
import com.wahidhidayat.themoviedb.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.fragment_video.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class VideoFragment : Fragment(), VideoContract.VideoView {

    private val presenter: VideoPresenter by inject {
        parametersOf(this)
    }

    private var listVideo: MutableList<VideoResult> = mutableListOf()
    private val videoAdapter = VideoAdapter(listVideo, context)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_video, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val detailActivity: DetailActivity = activity as DetailActivity
        val movie = detailActivity.getMovie()
        val movieId = movie.id

        rv_videos.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            adapter = videoAdapter
        }

        if (movieId != null) {
            fetchVideos(movieId, "en-US")
        }
    }

    private fun fetchVideos(movieId: Int, language: String) {
        presenter.fetchVideos(movieId, language)
    }

    override fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun attachToVideos(videos: List<VideoResult>?) {
        if (videos != null) {
            listVideo.addAll(videos)
            videoAdapter.notifyDataSetChanged()
        }
    }
}