package com.wahidhidayat.themoviedb.ui.video

import com.wahidhidayat.themoviedb.BuildConfig
import com.wahidhidayat.themoviedb.model.Videos
import com.wahidhidayat.themoviedb.network.APIEndpoints
import com.wahidhidayat.themoviedb.network.APIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VideoPresenter(v: VideoContract.VideoView) {
    private var view: VideoContract.VideoView? = v

    fun fetchVideos(movieId: Int, language: String) {
        val service = APIService.buildService(APIEndpoints::class.java)
        val call = service.getVideos(movieId, BuildConfig.API_KEY, language)
        call.enqueue(object : Callback<Videos> {
            override fun onResponse(call: Call<Videos>, response: Response<Videos>) {
                if (response.isSuccessful) {
                    view?.attachToVideos(response.body()?.results)
                } else {
                    view?.showToast("Something went wrong, try again later")
                }
            }

            override fun onFailure(call: Call<Videos>, t: Throwable?) {
                view?.showToast(t?.localizedMessage.toString())
            }
        })
    }
}