package com.wahidhidayat.themoviedb.ui.video

import com.wahidhidayat.themoviedb.model.VideoResult

interface VideoContract {
    interface VideoView {
        fun showToast(message: String)
        fun attachToVideos(videos: List<VideoResult>?)
    }
}