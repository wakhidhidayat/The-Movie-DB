package com.wahidhidayat.themoviedb.ui.info

import com.wahidhidayat.themoviedb.model.Cast

interface InfoContract {
    interface InfoView {
        fun showLoading()
        fun hideLoading()
        fun showToast(message: String)
        fun attachToCast(casts: List<Cast>?)
        fun setOverview(overview: String)
        fun setRelease(release: String)
        fun setRatting(ratting: String)
    }
}