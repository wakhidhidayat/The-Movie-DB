package com.wahidhidayat.themoviedb.ui.search

import com.wahidhidayat.themoviedb.model.MovieResult

interface SearchContract {
    interface SearchView {
        fun showLoading()
        fun hideLoading()
        fun showToast(message: String)
        fun attachToResult(movie: List<MovieResult>?)
    }
}