package com.wahidhidayat.themoviedb.ui.detail

interface DetailContract {
    interface DetailView {
        fun showLoading()
        fun hideLoading()
        fun showToast(message: String)
        fun setDetailTitle(title: String)
        fun setMovieBackrdop(imageUrl: String)
    }
}