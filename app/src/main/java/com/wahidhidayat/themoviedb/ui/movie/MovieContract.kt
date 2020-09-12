package com.wahidhidayat.themoviedb.ui.movie

import com.wahidhidayat.themoviedb.model.MovieResult

interface MovieContract {
    interface MovieView {
        fun showLoading()
        fun hideLoading()
        fun showToast(message: String)
        fun attachToUpcoming(movie: List<MovieResult>?)
        fun attachToNowPlaying(movie: List<MovieResult>?)
        fun attachToPopular(movie: List<MovieResult>?)
    }
}