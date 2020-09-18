package com.wahidhidayat.themoviedb.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.wahidhidayat.themoviedb.BuildConfig
import com.wahidhidayat.themoviedb.R
import com.wahidhidayat.themoviedb.adapter.NowPlayingAdapter
import com.wahidhidayat.themoviedb.adapter.PopularAdapter
import com.wahidhidayat.themoviedb.adapter.UpcomingAdapter
import com.wahidhidayat.themoviedb.model.MovieResult
import kotlinx.android.synthetic.main.fragment_movie.*
import org.koin.android.ext.android.inject
import org.koin.core.KoinComponent
import org.koin.core.parameter.parametersOf

class MovieFragment : Fragment(), MovieContract.MovieView, KoinComponent {
    private val presenter: MoviePresenter by inject {
        parametersOf(this)
    }

    private var listNowPlaying: MutableList<MovieResult> = mutableListOf()
    private val nowPlayingAdapter = NowPlayingAdapter(listNowPlaying, context)

    private var listUpcoming: MutableList<MovieResult> = mutableListOf()
    private val upcomingAdapter = UpcomingAdapter(listUpcoming, context)

    private var listPopular: MutableList<MovieResult> = mutableListOf()
    private val popularAdapter = PopularAdapter(listPopular, context)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showUpcoming()
        showNowPlaying()
        showPopular()

        rv_upcoming.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = upcomingAdapter
        }

        rv_now_playing.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = nowPlayingAdapter
        }

        rv_popular.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = popularAdapter
        }
    }

    private fun showUpcoming() {
        presenter.showUpcoming(BuildConfig.API_KEY, "en-US")
    }

    private fun showNowPlaying() {
        presenter.showNowPlaying(BuildConfig.API_KEY, "en-US")
    }

    private fun showPopular() {
        presenter.showPopular(BuildConfig.API_KEY, "en-US")
    }

    override fun showLoading() {
        if (pb_movie != null) {
            pb_movie.visibility = View.VISIBLE
        }
    }

    override fun hideLoading() {
        if (pb_movie != null) {
            pb_movie.visibility = View.GONE
        }
    }

    override fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun attachToUpcoming(movie: List<MovieResult>?) {
        if (movie != null) {
            listUpcoming.addAll(movie)
            upcomingAdapter.notifyDataSetChanged()
        }
    }

    override fun attachToNowPlaying(movie: List<MovieResult>?) {
        if (movie != null) {
            listNowPlaying.addAll(movie)
            nowPlayingAdapter.notifyDataSetChanged()
        }
    }

    override fun attachToPopular(movie: List<MovieResult>?) {
        if (movie != null) {
            listPopular.addAll(movie)
            popularAdapter.notifyDataSetChanged()
        }
    }
}